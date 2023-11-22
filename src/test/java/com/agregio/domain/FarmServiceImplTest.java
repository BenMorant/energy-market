package com.agregio.domain;

import com.agregio.database.FarmRepository;
import com.agregio.database.entity.FarmEntity;
import com.agregio.domain.model.Farm;
import com.agregio.domain.model.FarmType;
import com.agregio.exception.FarmTypeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Farm service")
class FarmServiceTest {

    @Mock
    private FarmRepository repository;

    @InjectMocks
    private FarmServiceImpl farmService;


    @Test
    @DisplayName("should create a farm")
    void shouldCreateFarm() {
        FarmEntity farmEntity = new FarmEntity();
        farmEntity.setFarmType(FarmType.HYDRO);
        farmEntity.setName("name");
        Farm farmToBeCreated = Farm.toCreate("hydro", "name");
        when(repository.save(any(FarmEntity.class))).thenReturn(farmEntity);
        Farm result = farmService.create(farmToBeCreated);
        assertThat(result.name()).isEqualTo(farmToBeCreated.name());
        assertThat(result.farmType()).isEqualTo(farmToBeCreated.farmType());

        verify(repository).save(any());
        verifyNoMoreInteractions(repository);

    }

    @Test
    @DisplayName("should throw a FarmTypeException when farmType doesn't exist")
    void shouldThrowFarmTypeException() {
        String invalidFarmTypeAsString = "toto";
        assertThatThrownBy(() -> {
            farmService.create(Farm.toCreate(invalidFarmTypeAsString, "name"));
        }).isInstanceOf(FarmTypeException.class)
                .hasMessageContaining(invalidFarmTypeAsString);
    }

}