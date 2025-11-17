package br.com.alura.tabelafipe.service;

public class GetUrl {

    private String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
    private String url;

    //obtains vehicle brands
    public String getUrl (String vehicleType){
        url = URL_BASE + vehicleType + "/marcas/";
        return url;
    }

    //Get vehicle models for a brand.
    public String getUrl (String vehicleType, int brandCode){
        url = URL_BASE + vehicleType + "/marcas/" + brandCode + "/modelos/";
        return url;
    }

    //Get the available years for the selected vehicle.
    public String getUrl (String vehicleType, int brandCode, int modelCode){
        url = URL_BASE + vehicleType + "/marcas/" + brandCode + "/modelos/" + modelCode + "/anos/";
        return url;
    }


}
