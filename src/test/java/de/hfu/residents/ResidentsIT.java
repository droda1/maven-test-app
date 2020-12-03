package de.hfu.residents;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentServiceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ResidentsIT {

    @Mock
    private ResidentRepository residentRepositoryMock;

    @InjectMocks
    private BaseResidentService residentServiceMock;

    @Before
    public void init() {
        when(residentRepositoryMock.getResidents()).thenReturn(createResidentList());
    }

    @Test
    public void getFilteredResidentList_returns_resident() {
        Resident filteredResident = new Resident ("Daniel", "Dronov", "Oskar straße", "Offenburg", new Date(2002, 11, 3));
        List<Resident> residentList = this.residentServiceMock.getFilteredResidentsList(filteredResident);

        Assert.assertEquals(1, residentList.size());
        Resident residentResult = residentList.get(0);
        Assert.assertEquals(residentResult.getFamilyName(), "Dronov");
        Assert.assertEquals(residentResult.getGivenName(), "Daniel");
        Assert.assertEquals(residentResult.getDateOfBirth(), new Date(2002, 11, 3));
    }

    @Test
    public void getFilteredResidentList_wildcarded_returns_resident() {
        Resident filteredResident = new Resident ("Dan*", "Dro*", "Oska*", "Offe*", new Date(2002, 11, 3));
        List<Resident> residentList = this.residentServiceMock.getFilteredResidentsList(filteredResident);

        Assert.assertEquals(1, residentList.size());
        Resident residentResult = residentList.get(0);
        Assert.assertEquals(residentResult.getFamilyName(), "Dronov");
        Assert.assertEquals(residentResult.getGivenName(), "Daniel");
        Assert.assertEquals(residentResult.getDateOfBirth(), new Date(2002, 11, 3));
    }

    @Test
    public void getFilteredResidentList_returns_no_result() {
        Resident notExistingResident = new Resident("Not", "existing", "Oskar straße",
                "Offenburg", new Date(2002, 11, 3));
        List<Resident> residentList = this.residentServiceMock.getFilteredResidentsList(notExistingResident);
        Assert.assertEquals(residentList.size(), 0);
    }

    @Test
    public void getUniqueResident_returns_result() throws ResidentServiceException {
        Resident filteredResident = new Resident ("Daniel", "Dronov", "Oskar straße", "Offenburg", new Date(2002, 11, 3));
        Resident residentResult = this.residentServiceMock.getUniqueResident(filteredResident);
        Assert.assertEquals(residentResult.getGivenName(), "Daniel");
        Assert.assertEquals(residentResult.getFamilyName(), "Dronov");
        Assert.assertEquals(residentResult.getDateOfBirth(), new Date(2002, 11, 3));
    }

    @Test
    public void getUniqueResident_wildcarded() throws ResidentServiceException {
        Resident filteredResident = new Resident ("Dan*", "Dro*", "Oska*", "Offe*", new Date(2002, 11, 3));
        try {
            this.residentServiceMock.getUniqueResident(filteredResident);
        } catch (ResidentServiceException e) {
            Assert.assertEquals(e.getMessage(), "Wildcards (*) sind nicht erlaubt!");
        }
    }

    @Test
    public void getUniqueResident_returns_no_result() throws ResidentServiceException {
        Resident filteredResident = new Resident("Not", "Existing", "Oskar straße", "Offenburg", new Date(2002, 11, 3));
        try {
            this.residentServiceMock.getUniqueResident(filteredResident);
        } catch (ResidentServiceException e) {
            Assert.assertEquals(e.getMessage(), "Suchanfrage lieferte kein eindeutiges Ergebnis!");
        }
    }

    private List<Resident> createResidentList() {
        List<Resident> residentList = new ArrayList<>();
        residentList.add(new Resident("Daniel", "Dronov", "Oskar straße", "Offenburg", new Date(2002, 11, 3)));
        residentList.add(new Resident("Jonas", "Kern", "Kernstraße", "Mundingen", new Date(2002, 11, 3)));
        residentList.add(new Resident("Sascha", "Könninger", "Wilhemlstraße", "Tuttlingen", new Date(2002, 11, 3)));
        residentList.add(new Resident("Frank", "Schling", "GHB-Weg", "Furtwangen", new Date(2002, 11, 3)));
        return residentList;
    }

}
