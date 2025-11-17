package br.com.alura.tabelafipe.principal;

import br.com.alura.tabelafipe.models.Data;
import br.com.alura.tabelafipe.models.VehiclesData;
import br.com.alura.tabelafipe.models.Models;
import br.com.alura.tabelafipe.models.Vehicle;
import br.com.alura.tabelafipe.service.GetDataAPI;
import br.com.alura.tabelafipe.service.ConvertData;
import br.com.alura.tabelafipe.service.GetUrl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private final Scanner reader = new Scanner(System.in);
    private final GetDataAPI getDataAPI = new GetDataAPI();
    private final ConvertData convertData = new ConvertData();
    private final GetUrl getUrl = new GetUrl();


    public void getMenu(){
        var menu = """
                Olá, Bem vindo a consulta de valores de Veículos.
                *** MENU ***
                1 - Carros
                2 - Motos
                3 - Caminhões
                
                Digite o código para o tipo de veículo: """;
        System.out.println(menu);
        int vehicleTypeCode = reader.nextInt();
        reader.nextLine();

        String vehicleType = getTypeVehicle(vehicleTypeCode);
        String url = getUrl.getUrl(vehicleType);
        var json = getDataAPI.getData(url);
        //System.out.println(json);
        List<Data> brandsData = getListData(json);
        brandsData.stream()
                .sorted(Comparator.comparing(Data::code))
                .forEach(m -> System.out.printf("\nCódigo: %s - Marca: %s",m.code(), m.name()));

        System.out.println("\n\nDigite a marca procurada: ");
        String excerptVehicleBrand = reader.nextLine();
        brandsData.stream()
                .filter(m -> m.name().toLowerCase().contains(excerptVehicleBrand.toLowerCase()))
                .forEach(m -> System.out.printf("\nCódigo: %s - Modelo: %s", m.code(), m.name()));

		System.out.println("\n\nDigite o código da marca: ");
		int brandCode = reader.nextInt();
        reader.nextLine();
        url = getUrl.getUrl(vehicleType,brandCode);
		json = getDataAPI.getData(url);
		System.out.println(json);
        Models modelsData = getModelsData(json);
        modelsData.models().stream()
                .sorted(Comparator.comparing(Data::code))
                .forEach(m -> System.out.printf("\nCódigo: %s - Modelo: %s", m.code(), m.name()));

        System.out.println("\n\nDigite o veículo procurado: ");
        String excerptVehicle = reader.nextLine();
        modelsData.models()
                .stream()
                .filter(m -> m.name().toLowerCase().contains(excerptVehicle.toLowerCase()))
                .forEach(m -> System.out.printf("\nCódigo: %s - Modelo: %s", m.code(), m.name()));

		System.out.println("\n\nDigite o código do modelo: ");
		int modelCode = reader.nextInt();
        reader.nextLine();
        url = getUrl.getUrl(vehicleType, brandCode, modelCode);
		json = getDataAPI.getData(url);
		//System.out.println(json);
        List<Data> yearsData = getListData(json);
        List<String> yearsCode = yearsData.stream()
                .map(Data::code)
                .toList();
        //System.out.println(codigoAnos);
        System.out.println("------------------");
        List<VehiclesData> vehiclesData = getVehiclesData(url, yearsCode);

        printSelectedVehicle(vehiclesData);

    }

    public String getTypeVehicle (int vehicleTypeCode){
        String vehicleType = "";
        switch (vehicleTypeCode){
            case 1:
                vehicleType = "carros";
                break;
            case 2:
                vehicleType = "motos";
                break;
            case 3:
                vehicleType = "caminhoes";
                break;
        }

        return vehicleType;
    }

    public void printSelectedVehicle (List<VehiclesData> vehiclesData){
        List<Vehicle> vehicles = vehiclesData.stream()
                .map(Vehicle::new)
                .toList();
        vehicles.forEach(System.out::println);
    }


    public List<VehiclesData> getVehiclesData (String url, List<String> yearsCode){
        List<VehiclesData> vehiclesData = new ArrayList<>();
        for(String yearCode : yearsCode){
            String newUrl = url + yearCode;
            var json = getDataAPI.getData(newUrl);
            vehiclesData.add(convertData.getData(json, VehiclesData.class));
        }

        return vehiclesData;
    }

    public List<Data> getListData(String json){
        return convertData.getList(json, Data.class);
    }

    public Models getModelsData(String json){
        return convertData.getData(json, Models.class);
    }
}
