package cdm.product.asset.validation.exists;

import cdm.base.staticdata.party.AncillaryRoleEnum;
import cdm.product.asset.DividendAmountTypeEnum;
import cdm.product.asset.DividendCompositionEnum;
import cdm.product.asset.DividendCurrency;
import cdm.product.asset.DividendEntitlementEnum;
import cdm.product.asset.DividendPayoutRatio;
import cdm.product.asset.DividendPeriod;
import cdm.product.asset.DividendPeriodEnum;
import cdm.product.asset.DividendReturnTerms;
import cdm.product.asset.NonCashDividendTreatmentEnum;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class DividendReturnTermsOnlyExistsValidator implements ValidatorWithArg<DividendReturnTerms, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends DividendReturnTerms> ValidationResult<DividendReturnTerms> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("dividendPayoutRatio", ExistenceChecker.isSet((List<? extends DividendPayoutRatio>) o.getDividendPayoutRatio()))
				.put("dividendReinvestment", ExistenceChecker.isSet((Boolean) o.getDividendReinvestment()))
				.put("dividendEntitlement", ExistenceChecker.isSet((DividendEntitlementEnum) o.getDividendEntitlement()))
				.put("dividendAmountType", ExistenceChecker.isSet((DividendAmountTypeEnum) o.getDividendAmountType()))
				.put("performance", ExistenceChecker.isSet((String) o.getPerformance()))
				.put("firstOrSecondPeriod", ExistenceChecker.isSet((DividendPeriodEnum) o.getFirstOrSecondPeriod()))
				.put("extraordinaryDividendsParty", ExistenceChecker.isSet((AncillaryRoleEnum) o.getExtraordinaryDividendsParty()))
				.put("excessDividendAmount", ExistenceChecker.isSet((DividendAmountTypeEnum) o.getExcessDividendAmount()))
				.put("dividendCurrency", ExistenceChecker.isSet((DividendCurrency) o.getDividendCurrency()))
				.put("nonCashDividendTreatment", ExistenceChecker.isSet((NonCashDividendTreatmentEnum) o.getNonCashDividendTreatment()))
				.put("dividendComposition", ExistenceChecker.isSet((DividendCompositionEnum) o.getDividendComposition()))
				.put("specialDividends", ExistenceChecker.isSet((Boolean) o.getSpecialDividends()))
				.put("materialDividend", ExistenceChecker.isSet((Boolean) o.getMaterialDividend()))
				.put("dividendPeriod", ExistenceChecker.isSet((List<? extends DividendPeriod>) o.getDividendPeriod()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("DividendReturnTerms", ValidationType.ONLY_EXISTS, "DividendReturnTerms", path, "");
		}
		return failure("DividendReturnTerms", ValidationType.ONLY_EXISTS, "DividendReturnTerms", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
