package cdm.base.staticdata.asset.common.validation.datarule;

import cdm.base.staticdata.asset.common.AssetType;
import cdm.base.staticdata.asset.common.AssetTypeEnum;
import cdm.base.staticdata.asset.common.DebtType;
import cdm.base.staticdata.asset.common.EquityTypeEnum;
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
@RosettaDataRule("AssetTypeSecuritySubType")
@ImplementedBy(AssetTypeSecuritySubType.Default.class)
public interface AssetTypeSecuritySubType extends Validator<AssetType> {
	
	String NAME = "AssetTypeSecuritySubType";
	String DEFINITION = "if assetType <> AssetTypeEnum -> Security then securityType is absent and debtType is absent and equityType is absent and fundType is absent";
	
	ValidationResult<AssetType> validate(RosettaPath path, AssetType assetType);
	
	class Default implements AssetTypeSecuritySubType {
	
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
				if (notEqual(MapperS.of(assetType).<AssetTypeEnum>map("getAssetType", _assetType -> _assetType.getAssetType()), MapperS.of(AssetTypeEnum.SECURITY), CardinalityOperator.Any).getOrDefault(false)) {
					return notExists(MapperS.of(assetType).<SecurityTypeEnum>map("getSecurityType", _assetType -> _assetType.getSecurityType())).and(notExists(MapperS.of(assetType).<DebtType>map("getDebtType", _assetType -> _assetType.getDebtType()))).and(notExists(MapperS.of(assetType).<EquityTypeEnum>map("getEquityType", _assetType -> _assetType.getEquityType()))).and(notExists(MapperS.of(assetType).<FundProductTypeEnum>map("getFundType", _assetType -> _assetType.getFundType())));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements AssetTypeSecuritySubType {
	
		@Override
		public ValidationResult<AssetType> validate(RosettaPath path, AssetType assetType) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "AssetType", path, DEFINITION);
		}
	}
}
