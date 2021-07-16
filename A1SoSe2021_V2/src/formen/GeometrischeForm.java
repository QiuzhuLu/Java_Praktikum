package formen;

public abstract class GeometrischeForm{
    private Punkt refPunkt;

    public GeometrischeForm(Punkt ref_punkt){
        this.refPunkt = ref_punkt;
    }

    public abstract void skaliere(int faktor);

    public void verschiebe(Punkt punkt){
        this.refPunkt = this.refPunkt.plus(punkt);
    }

    public void verschiebe(){
        verschiebe(new Punkt(1,1));
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ":rp=" + refPunkt;
    }

}
