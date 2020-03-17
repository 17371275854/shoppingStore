package com.qf;


import java.io.Serializable;

public class Order implements Serializable {

  private String id;
  private String account;
  private double rebate;//折扣
  private String createdate;//创建日期
  private String status;// 订单状态
  private String refundStatus;
  private String paystatus;// 订单支付状态
  private String lowStocks;//n:库存不足；y:库存充足。默认n
  private String remark;//订单支付时候显示的文字
  private String amount;// 订单总金额
  private int amountExchangeScore;//订单总兑换积分
  private String fee;// 总配送费
  private String ptotal;//商品总金额
  private int quantity;//商品总数量
  private String pname;// 商品图片
  private String expressCode;//提交订单时候选中的配送方式
  private String expressName;//配送方式名称
  private String expressNo;//物流单号
  private String expressCompanyName;//物流公司名称
  private String confirmSendProductRemark;
  private String otherRequirement;//客户附加要求
  private String closedComment;//此订单的所有订单项对应的商品都进行了评论，则此值为y，表示此订单的评论功能已经关闭，默认为n，在订单状态为已签收后，订单可以进行评价。
  private int score;//订单能获得的积分	/*** 订单状态:已成功下单*/
  public static final String order_status_init = "init";	/*** 订单状态:已审核*/
  public static final String order_status_pass = "pass";	/*** 订单状态:已发货*/
  public static final String order_status_send = "send";	/*** 订单状态:已签收*/
  public static final String order_status_sign = "sign";	/*** 订单状态:已取消*/
  public static final String order_status_cancel = "cancel";	/*** 订单状态:已归档*/
  public static final String order_status_file = "file";
  public static final String order_status_init_chinese = "已下单";
  public static final String order_status_pass_chinese = "已审核";
  public static final String order_status_send_chinese = "已发货";
  public static final String order_status_sign_chinese = "已签收";
  public static final String order_status_cancel_chinese = "已取消";
  public static final String order_status_file_chinese = "已归档";	/** 订单支付状态:未支付*/
  public static final String order_paystatus_n = "n";//	/** 订单支付状态:部分支付*/
  public static final String order_paystatus_p = "p";//	/** 订单支付状态:全部支付*/
  public static final String order_paystatus_y = "y";//	/**	 * 订单是否缺货状态	 */
  public static final String order_lowStocks_y = "y";//订单中存在商品缺货
  public static final String order_lowStocks_n = "n";//不缺货	/**	 * 订单评论状态是否关闭	 */
  public static String order_closedComment_y = "y";//已关闭
  public static String order_closedComment_n = "n";//未关闭

  public Order() {
  }

  public Order(String id, String account, double rebate, String createdate, String status, String refundStatus, String paystatus, String lowStocks, String remark, String amount, int amountExchangeScore, String fee, String ptotal, int quantity, String pname, String expressCode, String expressName, String expressNo, String expressCompanyName, String confirmSendProductRemark, String otherRequirement, String closedComment, int score) {
    this.id = id;
    this.account = account;
    this.rebate = rebate;
    this.createdate = createdate;
    this.status = status;
    this.refundStatus = refundStatus;
    this.paystatus = paystatus;
    this.lowStocks = lowStocks;
    this.remark = remark;
    this.amount = amount;
    this.amountExchangeScore = amountExchangeScore;
    this.fee = fee;
    this.ptotal = ptotal;
    this.quantity = quantity;
    this.pname = pname;
    this.expressCode = expressCode;
    this.expressName = expressName;
    this.expressNo = expressNo;
    this.expressCompanyName = expressCompanyName;
    this.confirmSendProductRemark = confirmSendProductRemark;
    this.otherRequirement = otherRequirement;
    this.closedComment = closedComment;
    this.score = score;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public double getRebate() {
    return rebate;
  }

  public void setRebate(double rebate) {
    this.rebate = rebate;
  }

  public String getCreatedate() {
    return createdate;
  }

  public void setCreatedate(String createdate) {
    this.createdate = createdate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getRefundStatus() {
    return refundStatus;
  }

  public void setRefundStatus(String refundStatus) {
    this.refundStatus = refundStatus;
  }

  public String getPaystatus() {
    return paystatus;
  }

  public void setPaystatus(String paystatus) {
    this.paystatus = paystatus;
  }

  public String getLowStocks() {
    return lowStocks;
  }

  public void setLowStocks(String lowStocks) {
    this.lowStocks = lowStocks;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public int getAmountExchangeScore() {
    return amountExchangeScore;
  }

  public void setAmountExchangeScore(int amountExchangeScore) {
    this.amountExchangeScore = amountExchangeScore;
  }

  public String getFee() {
    return fee;
  }

  public void setFee(String fee) {
    this.fee = fee;
  }

  public String getPtotal() {
    return ptotal;
  }

  public void setPtotal(String ptotal) {
    this.ptotal = ptotal;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public String getPname() {
    return pname;
  }

  public void setPname(String pname) {
    this.pname = pname;
  }

  public String getExpressCode() {
    return expressCode;
  }

  public void setExpressCode(String expressCode) {
    this.expressCode = expressCode;
  }

  public String getExpressName() {
    return expressName;
  }

  public void setExpressName(String expressName) {
    this.expressName = expressName;
  }

  public String getExpressNo() {
    return expressNo;
  }

  public void setExpressNo(String expressNo) {
    this.expressNo = expressNo;
  }

  public String getExpressCompanyName() {
    return expressCompanyName;
  }

  public void setExpressCompanyName(String expressCompanyName) {
    this.expressCompanyName = expressCompanyName;
  }

  public String getConfirmSendProductRemark() {
    return confirmSendProductRemark;
  }

  public void setConfirmSendProductRemark(String confirmSendProductRemark) {
    this.confirmSendProductRemark = confirmSendProductRemark;
  }

  public String getOtherRequirement() {
    return otherRequirement;
  }

  public void setOtherRequirement(String otherRequirement) {
    this.otherRequirement = otherRequirement;
  }

  public String getClosedComment() {
    return closedComment;
  }

  public void setClosedComment(String closedComment) {
    this.closedComment = closedComment;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public static String getOrder_status_init() {
    return order_status_init;
  }

  public static String getOrder_status_pass() {
    return order_status_pass;
  }

  public static String getOrder_status_send() {
    return order_status_send;
  }

  public static String getOrder_status_sign() {
    return order_status_sign;
  }

  public static String getOrder_status_cancel() {
    return order_status_cancel;
  }

  public static String getOrder_status_file() {
    return order_status_file;
  }

  public static String getOrder_status_init_chinese() {
    return order_status_init_chinese;
  }

  public static String getOrder_status_pass_chinese() {
    return order_status_pass_chinese;
  }

  public static String getOrder_status_send_chinese() {
    return order_status_send_chinese;
  }

  public static String getOrder_status_sign_chinese() {
    return order_status_sign_chinese;
  }

  public static String getOrder_status_cancel_chinese() {
    return order_status_cancel_chinese;
  }

  public static String getOrder_status_file_chinese() {
    return order_status_file_chinese;
  }

  public static String getOrder_paystatus_n() {
    return order_paystatus_n;
  }

  public static String getOrder_paystatus_p() {
    return order_paystatus_p;
  }

  public static String getOrder_paystatus_y() {
    return order_paystatus_y;
  }

  public static String getOrder_lowStocks_y() {
    return order_lowStocks_y;
  }

  public static String getOrder_lowStocks_n() {
    return order_lowStocks_n;
  }

  public static String getOrder_closedComment_y() {
    return order_closedComment_y;
  }

  public static void setOrder_closedComment_y(String order_closedComment_y) {
    Order.order_closedComment_y = order_closedComment_y;
  }

  public static String getOrder_closedComment_n() {
    return order_closedComment_n;
  }

  public static void setOrder_closedComment_n(String order_closedComment_n) {
    Order.order_closedComment_n = order_closedComment_n;
  }
}
