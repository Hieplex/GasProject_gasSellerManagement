package gasproject.my;

public class Gas {
    private String Gascanweight;
    private String GascanTrademark;
    private String GascanColor;
    private long ID;

    public Gas() {

    }
//    public long getID() {
//
//        return ID;
//    }
//
//    public void setID(long ID) {
//
//        this.ID = ID;
//    }

        public String getGascanweight() {
            return Gascanweight;
        }
        public void setGascanweight(String Gascanweight) {
            this.Gascanweight = Gascanweight;
        }

        public String getGascanTrademark() {
            return GascanTrademark;
        }

        public void setGascanTrademark(String GascanTrademark) {
            this.GascanTrademark = GascanTrademark;
        }
        public String getGascanColor() {
            return GascanColor;
        }
        public void setGascanColor(String GascanColor) {
            this.GascanColor = GascanColor;
        }
}
