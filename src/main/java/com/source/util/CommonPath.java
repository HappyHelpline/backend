package com.source.util;

public class CommonPath {

	public static String mailrequesturl = "http://api.maileracl.co.in/v2.3/?mail/send";

	public static String apikey = "77ec089daa53bb536f48420725d0196c";

	public static String apipasswd = "Prashant@123";

	public static String fromemail = "manager@happyhelping.com";

	public static String forregister = "Thank You For Registeration HappyHelpLine";

	public static String content_sendmail = "Registeration HappyHelpLine";

	public static String format = "1";

	public static String contentsource = "1";

	public static String autounsubscribelink = "1";

	public static String subject = "Registration";

	public static String appointment = "Appointment";

	public static String fromname = "HappyHelpLine";

	public static String content = "Registration";
	
	public static String status = "Status";

	public static String mobile = "9898889988";

	public static String landline = "+91-0124-4370000";

	public static String getTextContent(String username, String loginid, String password) {
		String textContent = "Dear " + username + ", \n"
				+ "Thank you for registering with http://thehappyhelpline.com/ . We will contact you soon. We look forward to a continued relationship.\n"
				+ "contact us at " + fromemail + " and mobile numbers " + mobile + "," + landline
				+ " for more details.\n" + "\n" + "Username =\t" + loginid + "\n" + "Password =\t" + password + "\n"
				+ "\n" + "Team HappyHelpline";

		return textContent;
	}

	public static String forAppointmentVOL(String volname,String clname,String id,String sch_date,String status) {
		String textContent = "Dear "+volname+", \n"
				+"This is to inform you that there is a new appointment request from "+clname+" at "+sch_date+"."
				+ "Please approve or reject according to your availability at  http://thehappyhelpline.com/. \n" 
				
				+"Regards,\n"
				+"HappyHelpLine"
				            ;
		return textContent;
	}
	
	public static String forAppointmentCL(String volname,String clname, String aspid,String sch_date,String status) {
		String textContent = "Dear "+clname+", \n"
				+"This is to inform you that there is a new appointment request from "+volname+" at "+sch_date+"."
				+ "More Detail To Open This  http://thehappyhelpline.com/.\n" 
				+"Regards,\n"
				+"HappyHelpLine"
				            ;
		return textContent;
	}
	
	public static String infoAppointmentVOL(String volname,String clname,String id,String sch_date,String status) {
		String textContent = "Dear "+volname+", \n"
				+"Sub: Your appointment has "+status+" with "+clname+" at " +sch_date +"\n"
				+"Regards,\n"
				+"HappyHelpLine";
		System.out.println(textContent);
		return textContent;
	}
	
	public static String infoAppointmentCL(String volname,String clname, String aspid,String sch_date,String status) {
		String textContent = "Dear "+clname+", \n"
				+"Sub: Your appointment has "+status+" with "+volname+" at " +sch_date +"\n"
				+"Regards,\n"
				+"HappyHelpLine";
		System.out.println(textContent);
		return textContent;
	}

}
