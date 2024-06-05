package cdm.product.template.validation.datarule;

import cdm.base.staticdata.asset.common.Security;
import cdm.product.template.AssetPayout;
import cdm.product.template.Product;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
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
@RosettaDataRule("AssetPayoutProductMustBeSecurity")
@ImplementedBy(AssetPayoutProductMustBeSecurity.Default.class)
public interface AssetPayoutProductMustBeSecurity extends Validator<AssetPayout> {
	
	String NAME = "AssetPayoutProductMustBeSecurity";
	String DEFINITION = "securityInformation -> security exists";
	
	ValidationResult<AssetPayout> validate(RosettaPath path, AssetPayout assetPayout);
	
	class Default implements AssetPayoutProductMustBeSecurity {
	
		@Override
		public ValidationResult<AssetPayout> validate(RosettaPath path, AssetPayout assetPayout) {
			ComparisonResult result = executeDataRule(assetPayout);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "AssetPayout", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "AssetPayout", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(AssetPayout assetPayout) {
			try {
				return exists(MapperS.of(assetPayout).<Product>map("getSecurityInformation", _assetPayout -> _assetPayout.getSecurityInformation()).<Security>map("getSecurity", product -> product.getSecurity()));
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements AssetPayoutProductMustBeSecurity {
	
		@Override
		public ValidationResult<AssetPayout> validate(RosettaPath path, AssetPayout assetPayout) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "AssetPayout", path, DEFINITION);
		}
	}
}
