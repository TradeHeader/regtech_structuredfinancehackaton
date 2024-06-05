package cdm.event.common.validation.datarule;

import cdm.event.common.CollateralPosition;
import cdm.event.common.CollateralStatusEnum;
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
@RosettaDataRule("CollateralPositionCollateralPositionStatusSettledOrInTransitOnly")
@ImplementedBy(CollateralPositionCollateralPositionStatusSettledOrInTransitOnly.Default.class)
public interface CollateralPositionCollateralPositionStatusSettledOrInTransitOnly extends Validator<CollateralPosition> {
	
	String NAME = "CollateralPositionCollateralPositionStatusSettledOrInTransitOnly";
	String DEFINITION = "if collateralPositionStatus exists then collateralPositionStatus = CollateralStatusEnum -> SettledAmount or collateralPositionStatus = CollateralStatusEnum -> InTransitAmount";
	
	ValidationResult<CollateralPosition> validate(RosettaPath path, CollateralPosition collateralPosition);
	
	class Default implements CollateralPositionCollateralPositionStatusSettledOrInTransitOnly {
	
		@Override
		public ValidationResult<CollateralPosition> validate(RosettaPath path, CollateralPosition collateralPosition) {
			ComparisonResult result = executeDataRule(collateralPosition);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CollateralPosition", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "CollateralPosition", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(CollateralPosition collateralPosition) {
			try {
				if (exists(MapperS.of(collateralPosition).<CollateralStatusEnum>map("getCollateralPositionStatus", _collateralPosition -> _collateralPosition.getCollateralPositionStatus())).getOrDefault(false)) {
					return areEqual(MapperS.of(collateralPosition).<CollateralStatusEnum>map("getCollateralPositionStatus", _collateralPosition -> _collateralPosition.getCollateralPositionStatus()), MapperS.of(CollateralStatusEnum.SETTLED_AMOUNT), CardinalityOperator.All).or(areEqual(MapperS.of(collateralPosition).<CollateralStatusEnum>map("getCollateralPositionStatus", _collateralPosition -> _collateralPosition.getCollateralPositionStatus()), MapperS.of(CollateralStatusEnum.IN_TRANSIT_AMOUNT), CardinalityOperator.All));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements CollateralPositionCollateralPositionStatusSettledOrInTransitOnly {
	
		@Override
		public ValidationResult<CollateralPosition> validate(RosettaPath path, CollateralPosition collateralPosition) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CollateralPosition", path, DEFINITION);
		}
	}
}
