package data.order;


import data.BaseResponse;

public class CreateOrderResponse extends BaseResponse {
    public String name;
    public Order order;

    public static class Order {
        public Integer number;

        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

