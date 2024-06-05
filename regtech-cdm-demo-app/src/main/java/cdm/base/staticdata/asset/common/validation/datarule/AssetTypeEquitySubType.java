package cdm.base.staticdata.asset.common.validation.datarule;

import cdm.base.staticdata.asset.common.AssetType;
import cdm.base.staticdata.asset.common.EquityTypeEnum;
import cdm.base.staticdata.asset.common.SecurityTypeEnum;
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
@RosettaDataRule("AssetTypeEquitySubType")
@ImplementedBy(AssetTypeEquitySubType.Default.class)
public interface AssetTypeEquitySubType extends Validator<AssetType> {
	
	String NAME = "AssetTypeEquitySubType";
	String DEFINITION = "if securityType <> SecurityTypeEnum -> Equity then equityType is absent";
	
	ValidationResult<AssetType> validate(RosettaPath path, AssetType assetType);
	
	class Default implements AssetTypeEquitySubType {
	
		@Override
		public ValidationResult<AssetType> validate(RosettaPath path, AssetType assetType) {
			ComparisonResult result = executeDataRule(assetType);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "AssetType", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "AssetType", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(AssetType assetType) {
			try {
				if (notEqual(MapperS.of(assetType).<SecurityTypeEnum>map("getSecurityType", _assetType -> _assetType.getSecurityType()), MapperS.of(SecurityTypeEnum.EQUITY), CardinalityOperator.Any).getOrDefault(false)) {
					return notExists(MapperS.of(assetType).<EquityTypeEnum>map("getEquityType", _assetType -> _assetType.getEquityType()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements AssetTypeEquitySubType {
	
		@Override
		public ValidationResult<AssetType> validate(RosettaPath path, AssetType assetType) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "AssetType", path, DEFINITION);
		}
	}
}
