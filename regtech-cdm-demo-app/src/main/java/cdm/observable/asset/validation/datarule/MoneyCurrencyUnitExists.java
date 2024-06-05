package cdm.observable.asset.validation.datarule;

import cdm.base.math.UnitType;
import cdm.observable.asset.Money;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.metafields.FieldWithMetaString;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("MoneyCurrencyUnitExists")
@ImplementedBy(MoneyCurrencyUnitExists.Default.class)
public interface MoneyCurrencyUnitExists extends Validator<Money> {
	
	String NAME = "MoneyCurrencyUnitExists";
	String DEFINITION = "unit -> currency exists";
	
	ValidationResult<Money> validate(RosettaPath path, Money money);
	
	class Default implements MoneyCurrencyUnitExists {
	
		@Override
		public ValidationResult<Money> validate(RosettaPath path, Money money) {
			ComparisonResult result = executeDataRule(money);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Money", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "Money", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(Money money) {
			try {
				return exists(MapperS.of(money).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).<FieldWithMetaString>map("getCurrency", unitType -> unitType.getCurrency()).<String>map("getValue", _f->_f.getValue()));
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements MoneyCurrencyUnitExists {
	
		@Override
		public ValidationResult<Money> validate(RosettaPath path, Money money) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Money", path, DEFINITION);
		}
	}
}
