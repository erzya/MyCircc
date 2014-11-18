public class ButtonModel {

    private String name;

    public ButtonModel(){
        setName("Button");
    }

    public ButtonModel(String name){
        setName(name);
    }


    public  String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}