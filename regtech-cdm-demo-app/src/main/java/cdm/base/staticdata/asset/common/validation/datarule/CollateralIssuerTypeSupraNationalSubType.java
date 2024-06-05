package cdm.base.staticdata.asset.common.validation.datarule;

import cdm.base.staticdata.asset.common.CollateralIssuerType;
import cdm.base.staticdata.asset.common.IssuerTypeEnum;
import cdm.base.staticdata.asset.common.SupraNationalIssuerTypeEnum;
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
@RosettaDataRule("CollateralIssuerTypeSupraNationalSubType")
@ImplementedBy(CollateralIssuerTypeSupraNationalSubType.Default.class)
public interface CollateralIssuerTypeSupraNationalSubType extends Validator<CollateralIssuerType> {
	
	String NAME = "CollateralIssuerTypeSupraNationalSubType";
	String DEFINITION = "if issuerType <> IssuerTypeEnum -> SupraNational then supraNationalType is absent";
	
	ValidationResult<CollateralIssuerType> validate(RosettaPath path, CollateralIssuerType collateralIssuerType);
	
	class Default implements CollateralIssuerTypeSupraNationalSubType {
	
		@Override
		public ValidationResult<CollateralIssuerType> validate(RosettaPath path, CollateralIssuerType collateralIssuerType) {
			ComparisonResult result = executeDataRule(collateralIssuerType);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CollateralIssuerType", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "CollateralIssuerType", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(CollateralIssuerType collateralIssuerType) {
			try {
				if (notEqual(MapperS.of(collateralIssuerType).<IssuerTypeEnum>map("getIssuerType", _collateralIssuerType -> _collateralIssuerType.getIssuerType()), MapperS.of(IssuerTypeEnum.SUPRA_NATIONAL), CardinalityOperator.Any).getOrDefault(false)) {
					return notExists(MapperS.of(collateralIssuerType).<SupraNationalIssuerTypeEnum>map("getSupraNationalType", _collateralIssuerType -> _collateralIssuerType.getSupraNationalType()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements CollateralIssuerTypeSupraNationalSubType {
	
		@Override
		public ValidationResult<CollateralIssuerType> validate(RosettaPath path, CollateralIssuerType collateralIssuerType) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CollateralIssuerType", path, DEFINITION);
		}
	}
}
