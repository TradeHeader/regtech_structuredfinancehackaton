package cdm.product.template.validation.datarule;

import cdm.product.template.AssetPayout;
import cdm.product.template.DividendTerms;
import cdm.product.template.Duration;
import cdm.product.template.DurationTypeEnum;
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
@RosettaDataRule("AssetPayoutDividendTermsValidation")
@ImplementedBy(AssetPayoutDividendTermsValidation.Default.class)
public interface AssetPayoutDividendTermsValidation extends Validator<AssetPayout> {
	
	String NAME = "AssetPayoutDividendTermsValidation";
	String DEFINITION = "if dividendTerms exists then durationType -> durationType = DurationTypeEnum -> Term";
	
	ValidationResult<AssetPayout> validate(RosettaPath path, AssetPayout assetPayout);
	
	class Default implements AssetPayoutDividendTermsValidation {
	
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
				if (exists(MapperS.of(assetPayout).<DividendTerms>map("getDividendTerms", _assetPayout -> _assetPayout.getDividendTerms())).getOrDefault(false)) {
					return areEqual(MapperS.of(assetPayout).<Duration>map("getDurationType", _assetPayout -> _assetPayout.getDurationType()).<DurationTypeEnum>map("getDurationType", duration -> duration.getDurationType()), MapperS.of(DurationTypeEnum.TERM), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements AssetPayoutDividendTermsValidation {
	
		@Override
		public ValidationResult<AssetPayout> validate(RosettaPath path, AssetPayout assetPayout) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "AssetPayout", path, DEFINITION);
		}
	}
}
