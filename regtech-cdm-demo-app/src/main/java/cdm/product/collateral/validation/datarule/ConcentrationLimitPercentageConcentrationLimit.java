package cdm.product.collateral.validation.datarule;

import cdm.base.math.NumberRange;
import cdm.product.collateral.ConcentrationLimit;
import cdm.product.collateral.ConcentrationLimitCriteria;
import cdm.product.collateral.ConcentrationLimitTypeEnum;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("ConcentrationLimitPercentageConcentrationLimit")
@ImplementedBy(ConcentrationLimitPercentageConcentrationLimit.Default.class)
public interface ConcentrationLimitPercentageConcentrationLimit extends Validator<ConcentrationLimit> {
	
	String NAME = "ConcentrationLimitPercentageConcentrationLimit";
	String DEFINITION = "if concentrationLimitCriteria -> concentrationLimitType only-element = ConcentrationLimitTypeEnum -> MarketCapitalisation then percentageLimit exists";
	
	ValidationResult<ConcentrationLimit> validate(RosettaPath path, ConcentrationLimit concentrationLimit);
	
	class Default implements ConcentrationLimitPercentageConcentrationLimit {
	
		@Override
		public ValidationResult<ConcentrationLimit> validate(RosettaPath path, ConcentrationLimit concentrationLimit) {
			ComparisonResult result = executeDataRule(concentrationLimit);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ConcentrationLimit", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "ConcentrationLimit", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(ConcentrationLimit concentrationLimit) {
			try {
				if (areEqual(MapperS.of(MapperS.of(concentrationLimit).<ConcentrationLimitCriteria>mapC("getConcentrationLimitCriteria", _concentrationLimit -> _concentrationLimit.getConcentrationLimitCriteria()).<ConcentrationLimitTypeEnum>map("getConcentrationLimitType", concentrationLimitCriteria -> concentrationLimitCriteria.getConcentrationLimitType()).get()), MapperS.of(ConcentrationLimitTypeEnum.MARKET_CAPITALISATION), CardinalityOperator.All).getOrDefault(false)) {
					return exists(MapperS.of(concentrationLimit).<NumberRange>map("getPercentageLimit", _concentrationLimit -> _concentrationLimit.getPercentageLimit()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ConcentrationLimitPercentageConcentrationLimit {
	
		@Override
		public ValidationResult<ConcentrationLimit> validate(RosettaPath path, ConcentrationLimit concentrationLimit) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ConcentrationLimit", path, DEFINITION);
		}
	}
}
