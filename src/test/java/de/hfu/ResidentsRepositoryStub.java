package de.hfu;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResidentsRepositoryStub implements ResidentRepository {
    @Override
    public List<Resident> getResidents(){
        List<Resident> residentList = new ArrayList<>();
        residentList.add(new Resident("Daniel", "Dronov", "Oskar straße", "Offenburg", new Date(2002, 11, 3)));
        residentList.add(new Resident("Jonas", "Kern", "Kernstraße", "Mundingen", new Date(2002, 11, 3)));
        residentList.add(new Resident("Sascha", "Könninger", "Wilhemlstraße", "Tuttlingen", new Date(2002, 11, 3)));
        residentList.add(new Resident("Frank", "Schling", "GHB-Weg", "Furtwangen", new Date(2002, 11, 3)));
        return residentList;
    }
}
