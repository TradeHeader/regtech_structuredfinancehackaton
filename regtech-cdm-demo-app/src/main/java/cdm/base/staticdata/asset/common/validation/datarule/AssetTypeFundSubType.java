package cdm.base.staticdata.asset.common.validation.datarule;

import cdm.base.staticdata.asset.common.AssetType;
import cdm.base.staticdata.asset.common.FundProductTypeEnum;
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
@RosettaDataRule("AssetTypeFundSubType")
@ImplementedBy(AssetTypeFundSubType.Default.class)
public interface AssetTypeFundSubType extends Validator<AssetType> {
	
	String NAME = "AssetTypeFundSubType";
	String DEFINITION = "if securityType <> SecurityTypeEnum -> Fund then fundType is absent";
	
	ValidationResult<AssetType> validate(RosettaPath path, AssetType assetType);
	
	class Default implements AssetTypeFundSubType {
	
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
				if (notEqual(MapperS.of(assetType).<SecurityTypeEnum>map("getSecurityType", _assetType -> _assetType.getSecurityType()), MapperS.of(SecurityTypeEnum.FUND), CardinalityOperator.Any).getOrDefault(false)) {
					return notExists(MapperS.of(assetType).<FundProductTypeEnum>map("getFundType", _assetType -> _assetType.getFundType()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements AssetTypeFundSubType {
	
		@Override
		public ValidationResult<AssetType> validate(RosettaPath path, AssetType assetType) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "AssetType", path, DEFINITION);
		}
	}
}
