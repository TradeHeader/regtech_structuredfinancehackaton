package cdm.product.collateral.validation.exists;

import cdm.base.datetime.BusinessCenterEnum;
import cdm.base.math.NumberBound;
import cdm.product.collateral.AlternativeToInterestAmountEnum;
import cdm.product.collateral.CollateralInterestHandlingEnum;
import cdm.product.collateral.CollateralInterestHandlingParameters;
import cdm.product.collateral.CollateralInterestNotification;
import cdm.product.collateral.InterestAmountApplication;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class CollateralInterestHandlingParametersOnlyExistsValidator implements ValidatorWithArg<CollateralInterestHandlingParameters, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CollateralInterestHandlingParameters> ValidationResult<CollateralInterestHandlingParameters> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("interestPaymentHandling", ExistenceChecker.isSet((CollateralInterestHandlingEnum) o.getInterestPaymentHandling()))
				.put("paymentBusinessCenter", ExistenceChecker.isSet((List<BusinessCenterEnum>) o.getPaymentBusinessCenter()))
				.put("netPostedAndHeldInterest", ExistenceChecker.isSet((Boolean) o.getNetPostedAndHeldInterest()))
				.put("netInterestWithMarginCalls", ExistenceChecker.isSet((Boolean) o.getNetInterestWithMarginCalls()))
				.put("includeAccrualInMarginCalc", ExistenceChecker.isSet((Boolean) o.getIncludeAccrualInMarginCalc()))
				.put("accrueInterestOnUnsettledInterest", ExistenceChecker.isSet((Boolean) o.getAccrueInterestOnUnsettledInterest()))
				.put("onFullReturn", ExistenceChecker.isSet((Boolean) o.getOnFullReturn()))
				.put("onPartialReturn", ExistenceChecker.isSet((Boolean) o.getOnPartialReturn()))
				.put("interestAmountApplication", ExistenceChecker.isSet((InterestAmountApplication) o.getInterestAmountApplication()))
				.put("interestRolloverLimit", ExistenceChecker.isSet((NumberBound) o.getInterestRolloverLimit()))
				.put("writeoffLimit", ExistenceChecker.isSet((NumberBound) o.getWriteoffLimit()))
				.put("alternativeToInterestAmount", ExistenceChecker.isSet((AlternativeToInterestAmountEnum) o.getAlternativeToInterestAmount()))
				.put("alternativeProvision", ExistenceChecker.isSet((String) o.getAlternativeProvision()))
				.put("cutoffTime", ExistenceChecker.isSet((LocalTime) o.getCutoffTime()))
				.put("notification", ExistenceChecker.isSet((CollateralInterestNotification) o.getNotification()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CollateralInterestHandlingParameters", ValidationType.ONLY_EXISTS, "CollateralInterestHandlingParameters", path, "");
		}
		return failure("CollateralInterestHandlingParameters", ValidationType.ONLY_EXISTS, "CollateralInterestHandlingParameters", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
