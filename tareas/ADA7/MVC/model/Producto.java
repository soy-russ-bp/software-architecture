package model;

class Producto{
    private String nombre;
    private int totalVotos;
    private String imageUrl;

    public Producto(String nombre, int totalVotos, String imageUrl){
        this.nombre=nombre;
        this.totalVotos=totalVotos;
        this.imageUrl=imageUrl;
    }

    public String getNombre(){
        return nombre;
    }
    public int getTotalVotos(){
        return totalVotos;
    }
    public String getImageUrl(){
        return imageUrl;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public void setTotalVotos(int totalVotos){
        this.totalVotos=totalVotos;
    }
    public void setImageUrl(String imageUrl){
        this.imageUrl=imageUrl;
    }

    public void archivoVotos(){
        //implementar
        

    }

}