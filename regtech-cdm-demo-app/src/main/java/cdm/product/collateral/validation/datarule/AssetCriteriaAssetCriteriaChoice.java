package cdm.product.collateral.validation.datarule;

import cdm.product.collateral.AssetCriteria;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ChoiceRuleValidationMethod;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.Arrays;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("AssetCriteriaAssetCriteriaChoice")
@ImplementedBy(AssetCriteriaAssetCriteriaChoice.Default.class)
public interface AssetCriteriaAssetCriteriaChoice extends Validator<AssetCriteria> {
	
	String NAME = "AssetCriteriaAssetCriteriaChoice";
	String DEFINITION = "optional choice collateralAssetType, collateralTaxonomy, productIdentifier";
	
	ValidationResult<AssetCriteria> validate(RosettaPath path, AssetCriteria assetCriteria);
	
	class Default implements AssetCriteriaAssetCriteriaChoice {
	
		@Override
		public ValidationResult<AssetCriteria> validate(RosettaPath path, AssetCriteria assetCriteria) {
			ComparisonResult result = executeDataRule(assetCriteria);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "AssetCriteria", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "AssetCriteria", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(AssetCriteria assetCriteria) {
			try {
				return choice(MapperS.of(assetCriteria), Arrays.asList("collateralAssetType", "collateralTaxonomy", "productIdentifier"), ChoiceRuleValidationMethod.OPTIONAL);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements AssetCriteriaAssetCriteriaChoice {
	
		@Override
		public ValidationResult<AssetCriteria> validate(RosettaPath path, AssetCriteria assetCriteria) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "AssetCriteria", path, DEFINITION);
		}
	}
}
