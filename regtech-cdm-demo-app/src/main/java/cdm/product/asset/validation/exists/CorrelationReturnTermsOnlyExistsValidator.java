package cdm.product.asset.validation.exists;

import cdm.base.math.NumberRange;
import cdm.observable.asset.DividendApplicability;
import cdm.observable.asset.Price;
import cdm.observable.common.DeterminationMethodEnum;
import cdm.product.asset.CorrelationReturnTerms;
import cdm.product.asset.EquityUnderlierProvisions;
import cdm.product.asset.ValuationTerms;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class CorrelationReturnTermsOnlyExistsValidator implements ValidatorWithArg<CorrelationReturnTerms, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CorrelationReturnTerms> ValidationResult<CorrelationReturnTerms> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("valuationTerms", ExistenceChecker.isSet((ValuationTerms) o.getValuationTerms()))
				.put("annualizationFactor", ExistenceChecker.isSet((Integer) o.getAnnualizationFactor()))
				.put("dividendApplicability", ExistenceChecker.isSet((DividendApplicability) o.getDividendApplicability()))
				.put("equityUnderlierProvisions", ExistenceChecker.isSet((EquityUnderlierProvisions) o.getEquityUnderlierProvisions()))
				.put("sharePriceDividendAdjustment", ExistenceChecker.isSet((Boolean) o.getSharePriceDividendAdjustment()))
				.put("expectedN", ExistenceChecker.isSet((Integer) o.getExpectedN()))
				.put("initialLevel", ExistenceChecker.isSet((BigDecimal) o.getInitialLevel()))
				.put("initialLevelSource", ExistenceChecker.isSet((DeterminationMethodEnum) o.getInitialLevelSource()))
				.put("meanAdjustment", ExistenceChecker.isSet((Boolean) o.getMeanAdjustment()))
				.put("performance", ExistenceChecker.isSet((String) o.getPerformance()))
				.put("correlationStrikePrice", ExistenceChecker.isSet((Price) o.getCorrelationStrikePrice()))
				.put("boundedCorrelation", ExistenceChecker.isSet((NumberRange) o.getBoundedCorrelation()))
				.put("numberOfDataSeries", ExistenceChecker.isSet((Integer) o.getNumberOfDataSeries()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CorrelationReturnTerms", ValidationType.ONLY_EXISTS, "CorrelationReturnTerms", path, "");
		}
		return failure("CorrelationReturnTerms", ValidationType.ONLY_EXISTS, "CorrelationReturnTerms", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
