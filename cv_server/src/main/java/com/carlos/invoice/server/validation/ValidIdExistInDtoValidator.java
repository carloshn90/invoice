package com.carlos.invoice.server.validation;

import com.carlos.invoice.server.dto.IUpgradableDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class ValidIdExistInDtoValidator implements ConstraintValidator<ValidIdExistInDto, Object[]> {

   public void initialize(ValidIdExistInDto constraint) {
   }

   public boolean isValid(Object[] values, ConstraintValidatorContext context) {

      if (values == null || values.length != 2) {
         return false;
      }

      if (!(values[0] instanceof Long) || !(values[1] instanceof IUpgradableDto)) {
         return false;
      }

      Long objectId = (Long)values[0];
      IUpgradableDto upgradableDto = (IUpgradableDto) values[1];

      return objectId.equals(upgradableDto.getId());
   }
}
