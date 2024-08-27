package com.techelevator.model;

public class Orders {

    private int orderId;

    private String name;
    private String secondIdeal;
    private String thirdIdeal;
    private String fourthIdeal;
    private String fifthIdeal;
    private String glyph;
    public Orders(){

    }

    public Orders(int order_id, String name, String secondIdeal, String thirdIdeal, String fourthIdeal, String fifthIdeal, String glyph) {
        this.orderId = order_id;
        this.name = name;
        this.secondIdeal = secondIdeal;
        this.thirdIdeal = thirdIdeal;
        this.fourthIdeal = fourthIdeal;
        this.fifthIdeal = fifthIdeal;
        this.glyph = glyph;

    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondIdeal() {
        return secondIdeal;
    }

    public void setSecondIdeal(String secondIdeal) {
        this.secondIdeal = secondIdeal;
    }

    public String getThirdIdeal() {
        return thirdIdeal;
    }

    public void setThirdIdeal(String thirdIdeal) {
        this.thirdIdeal = thirdIdeal;
    }

    public String getFourthIdeal() {
        return fourthIdeal;
    }

    public void setFourthIdeal(String fourthIdeal) {
        this.fourthIdeal = fourthIdeal;
    }

    public String getFifthIdeal() {
        return fifthIdeal;
    }

    public void setFifthIdeal(String fifthIdeal) {
        this.fifthIdeal = fifthIdeal;
    }

    public String getGlyph() {
        return glyph;
    }

    public void setGlyph(String glyph) {
        this.glyph = glyph;
    }

    @Override
    public String toString(){
        return "orders{" +
                "order_id = " + orderId +
                ", order_name = '" + name + '\'' +
                '}';
    }
}
