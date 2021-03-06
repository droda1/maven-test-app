package de.hfu;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentServiceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class ResidentsStubTest {

    private ResidentsRepositoryStub residentsRepositoryStub = new ResidentsRepositoryStub();
    private BaseResidentService baseResidentService = new BaseResidentService();

    @Before
    public void init() {
        this.baseResidentService.setResidentRepository(residentsRepositoryStub);
    }

    @Test
    public void getFilteredResidentList_returns_resident() {
        Resident filteredResident = new Resident ("Daniel", "Dronov", "Oskar straße", "Offenburg", new Date(2002, 11, 3));
        List<Resident> residentList = this.baseResidentService.getFilteredResidentsList(filteredResident);

        Assert.assertEquals(1, residentList.size());
        Resident residentResult = residentList.get(0);
        Assert.assertEquals(residentResult.getFamilyName(), "Dronov");
        Assert.assertEquals(residentResult.getGivenName(), "Daniel");
        Assert.assertEquals(residentResult.getDateOfBirth(), new Date(2002, 11, 3));
    }

    @Test
    public void getFilteredResidentList_wildcarded_returns_resident() {
        Resident filteredResident = new Resident ("Dan*", "Dro*", "Oska*", "Offe*", new Date(2002, 11, 3));
        List<Resident> residentList = this.baseResidentService.getFilteredResidentsList(filteredResident);

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
        List<Resident> residentList = this.baseResidentService.getFilteredResidentsList(notExistingResident);
        Assert.assertEquals(residentList.size(), 0);
    }

    @Test
    public void getUniqueResident_returns_result() throws ResidentServiceException {
        Resident filteredResident = new Resident ("Daniel", "Dronov", "Oskar straße", "Offenburg", new Date(2002, 11, 3));
        Resident residentResult = this.baseResidentService.getUniqueResident(filteredResident);
        Assert.assertEquals(residentResult.getGivenName(), "Daniel");
        Assert.assertEquals(residentResult.getFamilyName(), "Dronov");
        Assert.assertEquals(residentResult.getDateOfBirth(), new Date(2002, 11, 3));
    }

    @Test
    public void getUniqueResident_wildcarded() throws ResidentServiceException {
        Resident filteredResident = new Resident ("Dan*", "Dro*", "Oska*", "Offe*", new Date(2002, 11, 3));
        try {
            this.baseResidentService.getUniqueResident(filteredResident);
        } catch (ResidentServiceException e) {
            Assert.assertEquals(e.getMessage(), "Wildcards (*) sind nicht erlaubt!");
        }
    }

    @Test
    public void getUniqueResident_returns_no_result() throws ResidentServiceException {
        Resident filteredResident = new Resident("Not", "Existing", "Oskar straße", "Offenburg", new Date(2002, 11, 3));
        try {
            this.baseResidentService.getUniqueResident(filteredResident);
        } catch (ResidentServiceException e) {
            Assert.assertEquals(e.getMessage(), "Suchanfrage lieferte kein eindeutiges Ergebnis!");
        }
    }

}
