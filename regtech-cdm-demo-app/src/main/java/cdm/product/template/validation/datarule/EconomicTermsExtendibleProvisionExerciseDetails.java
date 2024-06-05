package cdm.product.template.validation.datarule;

import cdm.product.template.AmericanExercise;
import cdm.product.template.AssetPayout;
import cdm.product.template.BermudaExercise;
import cdm.product.template.EconomicTerms;
import cdm.product.template.EuropeanExercise;
import cdm.product.template.ExtendibleProvision;
import cdm.product.template.Payout;
import cdm.product.template.TerminationProvision;
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
@RosettaDataRule("EconomicTermsExtendibleProvisionExerciseDetails")
@ImplementedBy(EconomicTermsExtendibleProvisionExerciseDetails.Default.class)
public interface EconomicTermsExtendibleProvisionExerciseDetails extends Validator<EconomicTerms> {
	
	String NAME = "EconomicTermsExtendibleProvisionExerciseDetails";
	String DEFINITION = "if payout -> assetPayout is absent then if terminationProvision -> extendibleProvision exists then (terminationProvision -> extendibleProvision -> americanExercise exists and terminationProvision -> extendibleProvision -> bermudaExercise is absent and terminationProvision -> extendibleProvision -> europeanExercise is absent) or (terminationProvision -> extendibleProvision -> americanExercise is absent and terminationProvision -> extendibleProvision -> bermudaExercise exists and terminationProvision -> extendibleProvision -> europeanExercise is absent) or (terminationProvision -> extendibleProvision -> americanExercise is absent and terminationProvision -> extendibleProvision -> bermudaExercise is absent and terminationProvision -> extendibleProvision -> europeanExercise exists) and terminationProvision -> extendibleProvision -> followUpConfirmation exists";
	
	ValidationResult<EconomicTerms> validate(RosettaPath path, EconomicTerms economicTerms);
	
	class Default implements EconomicTermsExtendibleProvisionExerciseDetails {
	
		@Override
		public ValidationResult<EconomicTerms> validate(RosettaPath path, EconomicTerms economicTerms) {
			ComparisonResult result = executeDataRule(economicTerms);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "EconomicTerms", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "EconomicTerms", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(EconomicTerms economicTerms) {
			try {
				if (notExists(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<AssetPayout>mapC("getAssetPayout", payout -> payout.getAssetPayout())).getOrDefault(false)) {
					if (exists(MapperS.of(economicTerms).<TerminationProvision>map("getTerminationProvision", _economicTerms -> _economicTerms.getTerminationProvision()).<ExtendibleProvision>map("getExtendibleProvision", terminationProvision -> terminationProvision.getExtendibleProvision())).getOrDefault(false)) {
						return exists(MapperS.of(economicTerms).<TerminationProvision>map("getTerminationProvision", _economicTerms -> _economicTerms.getTerminationProvision()).<ExtendibleProvision>map("getExtendibleProvision", terminationProvision -> terminationProvision.getExtendibleProvision()).<AmericanExercise>map("getAmericanExercise", extendibleProvision -> extendibleProvision.getAmericanExercise())).and(notExists(MapperS.of(economicTerms).<TerminationProvision>map("getTerminationProvision", _economicTerms -> _economicTerms.getTerminationProvision()).<ExtendibleProvision>map("getExtendibleProvision", terminationProvision -> terminationProvision.getExtendibleProvision()).<BermudaExercise>map("getBermudaExercise", extendibleProvision -> extendibleProvision.getBermudaExercise()))).and(notExists(MapperS.of(economicTerms).<TerminationProvision>map("getTerminationProvision", _economicTerms -> _economicTerms.getTerminationProvision()).<ExtendibleProvision>map("getExtendibleProvision", terminationProvision -> terminationProvision.getExtendibleProvision()).<EuropeanExercise>map("getEuropeanExercise", extendibleProvision -> extendibleProvision.getEuropeanExercise()))).or(notExists(MapperS.of(economicTerms).<TerminationProvision>map("getTerminationProvision", _economicTerms -> _economicTerms.getTerminationProvision()).<ExtendibleProvision>map("getExtendibleProvision", terminationProvision -> terminationProvision.getExtendibleProvision()).<AmericanExercise>map("getAmericanExercise", extendibleProvision -> extendibleProvision.getAmericanExercise())).and(exists(MapperS.of(economicTerms).<TerminationProvision>map("getTerminationProvision", _economicTerms -> _economicTerms.getTerminationProvision()).<ExtendibleProvision>map("getExtendibleProvision", terminationProvision -> terminationProvision.getExtendibleProvision()).<BermudaExercise>map("getBermudaExercise", extendibleProvision -> extendibleProvision.getBermudaExercise()))).and(notExists(MapperS.of(economicTerms).<TerminationProvision>map("getTerminationProvision", _economicTerms -> _economicTerms.getTerminationProvision()).<ExtendibleProvision>map("getExtendibleProvision", terminationProvision -> terminationProvision.getExtendibleProvision()).<EuropeanExercise>map("getEuropeanExercise", extendibleProvision -> extendibleProvision.getEuropeanExercise())))).or(notExists(MapperS.of(economicTerms).<TerminationProvision>map("getTerminationProvision", _economicTerms -> _economicTerms.getTerminationProvision()).<ExtendibleProvision>map("getExtendibleProvision", terminationProvision -> terminationProvision.getExtendibleProvision()).<AmericanExercise>map("getAmericanExercise", extendibleProvision -> extendibleProvision.getAmericanExercise())).and(notExists(MapperS.of(economicTerms).<TerminationProvision>map("getTerminationProvision", _economicTerms -> _economicTerms.getTerminationProvision()).<ExtendibleProvision>map("getExtendibleProvision", terminationProvision -> terminationProvision.getExtendibleProvision()).<BermudaExercise>map("getBermudaExercise", extendibleProvision -> extendibleProvision.getBermudaExercise()))).and(exists(MapperS.of(economicTerms).<TerminationProvision>map("getTerminationProvision", _economicTerms -> _economicTerms.getTerminationProvision()).<ExtendibleProvision>map("getExtendibleProvision", terminationProvision -> terminationProvision.getExtendibleProvision()).<EuropeanExercise>map("getEuropeanExercise", extendibleProvision -> extendibleProvision.getEuropeanExercise()))).and(exists(MapperS.of(economicTerms).<TerminationProvision>map("getTerminationProvision", _economicTerms -> _economicTerms.getTerminationProvision()).<ExtendibleProvision>map("getExtendibleProvision", terminationProvision -> terminationProvision.getExtendibleProvision()).<Boolean>map("getFollowUpConfirmation", extendibleProvision -> extendibleProvision.getFollowUpConfirmation()))));
					}
					return ComparisonResult.successEmptyOperand("");
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements EconomicTermsExtendibleProvisionExerciseDetails {
	
		@Override
		public ValidationResult<EconomicTerms> validate(RosettaPath path, EconomicTerms economicTerms) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "EconomicTerms", path, DEFINITION);
		}
	}
}
