package adapter;


import adapter.Converter;

public class USDLBPConverter implements Converter{
    private float rate = 90000;
    @Override
    public float convert(float amount) {
        return amount/rate;
    }
}
