package cdm.product.asset.validation.datarule;

import cdm.observable.asset.Price;
import cdm.product.asset.CreditDefaultPayout;
import cdm.product.asset.GeneralTerms;
import cdm.product.asset.ReferenceInformation;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.math.BigDecimal;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("CreditDefaultPayoutFpML_cd_12")
@ImplementedBy(CreditDefaultPayoutFpMLCd12.Default.class)
public interface CreditDefaultPayoutFpMLCd12 extends Validator<CreditDefaultPayout> {
	
	String NAME = "CreditDefaultPayoutFpML_cd_12";
	String DEFINITION = "if generalTerms -> referenceInformation -> referencePrice exists then generalTerms -> referenceInformation -> referencePrice -> value >= 0";
	
	ValidationResult<CreditDefaultPayout> validate(RosettaPath path, CreditDefaultPayout creditDefaultPayout);
	
	class Default implements CreditDefaultPayoutFpMLCd12 {
	
		@Override
		public ValidationResult<CreditDefaultPayout> validate(RosettaPath path, CreditDefaultPayout creditDefaultPayout) {
			ComparisonResult result = executeDataRule(creditDefaultPayout);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CreditDefaultPayout", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "CreditDefaultPayout", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(CreditDefaultPayout creditDefaultPayout) {
			try {
				if (exists(MapperS.of(creditDefaultPayout).<GeneralTerms>map("getGeneralTerms", _creditDefaultPayout -> _creditDefaultPayout.getGeneralTerms()).<ReferenceInformation>map("getReferenceInformation", generalTerms -> generalTerms.getReferenceInformation()).<Price>map("getReferencePrice", referenceInformation -> referenceInformation.getReferencePrice())).getOrDefault(false)) {
					return greaterThanEquals(MapperS.of(creditDefaultPayout).<GeneralTerms>map("getGeneralTerms", _creditDefaultPayout -> _creditDefaultPayout.getGeneralTerms()).<ReferenceInformation>map("getReferenceInformation", generalTerms -> generalTerms.getReferenceInformation()).<Price>map("getReferencePrice", referenceInformation -> referenceInformation.getReferencePrice()).<BigDecimal>map("getValue", measureBase -> measureBase.getValue()), MapperS.of(BigDecimal.valueOf(0)), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements CreditDefaultPayoutFpMLCd12 {
	
		@Override
		public ValidationResult<CreditDefaultPayout> validate(RosettaPath path, CreditDefaultPayout creditDefaultPayout) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CreditDefaultPayout", path, DEFINITION);
		}
	}
}
