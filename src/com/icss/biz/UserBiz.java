package com.icss.biz;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;


import com.icss.dao.BookDao;
import com.icss.dao.UserDao;
import com.icss.dto.Buyinfo;
import com.icss.entity.TUser;
import com.icss.entity.TBook;
import com.icss.entity.TOrderDetail;
public class UserBiz {
	
	//多表查询
	public List<Buyinfo> lookUserBuyinfo(String uname,String begindate,String endDate) throws Exception{
		List<Buyinfo> info=null;
		UserDao dao=new UserDao();
		try {
			info=dao.lookUserBuyinfo(uname, begindate, endDate);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dao.closeConn();
		}
		
		return info;
		
	}

	public void buyBooks(String uname,int allmoney,Map<String,Integer> shopcar) throws Exception{
		Integer leftMoney=0;
		if(uname !=null && shopcar !=null) {
			UserDao userdao=new UserDao();
			try {
			userdao.beginTransaction();
			//1.用户扣款
			userdao.updateAccount(uname,allmoney);
			System.out.println("user"+uname+",money"+allmoney);
				//2.生成一条订单
				BookDao bookDao=new BookDao();
				bookDao.setConn(userdao.getConn());
				String orderid=bookDao.createOrder(uname, allmoney);
				//3.生成多条订单
				List<TBook> books=bookDao.getShopcarBooks(shopcar.keySet());
				for(TBook bk:books) {
					TOrderDetail detail=new TOrderDetail();
					detail.setOid(orderid);
					detail.setBuycount(shopcar.get(bk.getIsbn()));
					detail.setIsbn(bk.getIsbn());
					detail.setDealprice(bk.getPrice());
					bookDao.addOrderDetail(detail);
				}
				userdao.commit();	
			
	
			}catch (Exception e) {
				userdao.rollback();
				e.printStackTrace();
			}finally {
				userdao.closeConn();
			}
			
			
		/*	Set<String> isbns=shopcar.keySet();
			for(String isbn:isbns) {
				TOrderDetail detail=new TOrderDetail();
				detail.setOid(orderid);
				detail.setBuycount(shopcar.get(isbn));
				detail.setIsbn(isbn);
				bookDao.addOrderDetail(detail);
			}*/
		}else {
			throw new Exception("can shu cuo wu");
		}
		
	}
	
	public TUser login(String uname,String pwd) throws Exception {
		TUser tUser=null;

		if(uname !=null && pwd != null && !uname.equals("") && !pwd.equals("")) {
			UserDao dao=new UserDao();
			try {
				tUser= dao.login(uname, pwd);

			} catch (Exception e) {
				throw e;
			}finally {
				dao.closeConn();
			}
		}else {
			throw new Exception("no input!!");
		}
		return tUser;
	}
}
