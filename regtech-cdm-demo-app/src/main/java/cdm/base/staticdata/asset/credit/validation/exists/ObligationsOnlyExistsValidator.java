package cdm.base.staticdata.asset.credit.validation.exists;

import cdm.base.staticdata.asset.credit.NotDomesticCurrency;
import cdm.base.staticdata.asset.credit.ObligationCategoryEnum;
import cdm.base.staticdata.asset.credit.Obligations;
import cdm.base.staticdata.asset.credit.SpecifiedCurrency;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class ObligationsOnlyExistsValidator implements ValidatorWithArg<Obligations, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends Obligations> ValidationResult<Obligations> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("category", ExistenceChecker.isSet((ObligationCategoryEnum) o.getCategory()))
				.put("notSubordinated", ExistenceChecker.isSet((Boolean) o.getNotSubordinated()))
				.put("specifiedCurrency", ExistenceChecker.isSet((SpecifiedCurrency) o.getSpecifiedCurrency()))
				.put("notSovereignLender", ExistenceChecker.isSet((Boolean) o.getNotSovereignLender()))
				.put("notDomesticCurrency", ExistenceChecker.isSet((NotDomesticCurrency) o.getNotDomesticCurrency()))
				.put("notDomesticLaw", ExistenceChecker.isSet((Boolean) o.getNotDomesticLaw()))
				.put("listed", ExistenceChecker.isSet((Boolean) o.getListed()))
				.put("notDomesticIssuance", ExistenceChecker.isSet((Boolean) o.getNotDomesticIssuance()))
				.put("fullFaithAndCreditObLiability", ExistenceChecker.isSet((Boolean) o.getFullFaithAndCreditObLiability()))
				.put("generalFundObligationLiability", ExistenceChecker.isSet((Boolean) o.getGeneralFundObligationLiability()))
				.put("revenueObligationLiability", ExistenceChecker.isSet((Boolean) o.getRevenueObligationLiability()))
				.put("notContingent", ExistenceChecker.isSet((Boolean) o.getNotContingent()))
				.put("excluded", ExistenceChecker.isSet((String) o.getExcluded()))
				.put("othReferenceEntityObligations", ExistenceChecker.isSet((String) o.getOthReferenceEntityObligations()))
				.put("designatedPriority", ExistenceChecker.isSet((FieldWithMetaString) o.getDesignatedPriority()))
				.put("cashSettlementOnly", ExistenceChecker.isSet((Boolean) o.getCashSettlementOnly()))
				.put("deliveryOfCommitments", ExistenceChecker.isSet((Boolean) o.getDeliveryOfCommitments()))
				.put("continuity", ExistenceChecker.isSet((Boolean) o.getContinuity()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("Obligations", ValidationType.ONLY_EXISTS, "Obligations", path, "");
		}
		return failure("Obligations", ValidationType.ONLY_EXISTS, "Obligations", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
