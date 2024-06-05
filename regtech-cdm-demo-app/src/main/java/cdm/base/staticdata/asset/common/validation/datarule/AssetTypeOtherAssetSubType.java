package cdm.base.staticdata.asset.common.validation.datarule;

import cdm.base.staticdata.asset.common.AssetType;
import cdm.base.staticdata.asset.common.AssetTypeEnum;
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
@RosettaDataRule("AssetTypeOtherAssetSubType")
@ImplementedBy(AssetTypeOtherAssetSubType.Default.class)
public interface AssetTypeOtherAssetSubType extends Validator<AssetType> {
	
	String NAME = "AssetTypeOtherAssetSubType";
	String DEFINITION = "if assetType = AssetTypeEnum -> Other then otherAssetType exists";
	
	ValidationResult<AssetType> validate(RosettaPath path, AssetType assetType);
	
	class Default implements AssetTypeOtherAssetSubType {
	
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
				if (areEqual(MapperS.of(assetType).<AssetTypeEnum>map("getAssetType", _assetType -> _assetType.getAssetType()), MapperS.of(AssetTypeEnum.OTHER), CardinalityOperator.All).getOrDefault(false)) {
					return exists(MapperS.of(assetType).<String>mapC("getOtherAssetType", _assetType -> _assetType.getOtherAssetType()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements AssetTypeOtherAssetSubType {
	
		@Override
		public ValidationResult<AssetType> validate(RosettaPath path, AssetType assetType) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "AssetType", path, DEFINITION);
		}
	}
}
