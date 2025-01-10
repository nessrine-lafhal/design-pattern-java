package test.monpackage.service;
import monpackage.beans.Filiere;
import monpackage.beans.Module;
import monpackage.dao.ElementDAO;
import monpackage.dao.ModuleDAO;
import monpackage.service.ModuleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ModuleServiceTest {
    private ModuleDAO moduleDAOMock;
    private ElementDAO elementDAOMock;
    private ModuleService moduleService;

    @BeforeEach
    void setUp() {
        moduleDAOMock = mock(ModuleDAO.class);
        elementDAOMock = mock(ElementDAO.class);
        // Injecter les mocks dans la classe de service
        moduleService = new ModuleService(moduleDAOMock, elementDAOMock);
    }

    @Test
    public void testCreateModule() {
        // Arrange
        String code = "M001";
        String nom = "Informatique";
        String semester = "S1";
        Filiere filiere = new Filiere("F001", "Génie Logiciel");

        // Act
        moduleService.createModule(code, nom, semester, filiere);

        // Assert
        verify(moduleDAOMock, times(1)).saveModule(any(Module.class));
    }
}
