package javabean;

public class Book {
	int bookID=0;//书籍编号
	String bookName=null;//书籍名
	int bookPrice=0;//书籍价格
	String introduction=null;//书籍详细信息
	String imgPath=null;//书籍对应的图片地址
	public Book(int bookID, String bookName, int bookPrice, String introduction,String imgPath) {
		super();
		this.bookID = bookID;
		this.bookName = bookName;
		this.bookPrice = bookPrice;
		this.introduction = introduction;
		this.imgPath=imgPath;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public int getBookID() {
		return bookID;
	}
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public int getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	
	
}
