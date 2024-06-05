package cdm.product.asset.validation.exists;

import cdm.product.asset.CreditIndexReferenceInformation;
import cdm.product.asset.CreditSeniorityEnum;
import cdm.product.asset.ReferenceInformation;
import cdm.product.asset.SettledEntityMatrix;
import cdm.product.asset.Tranche;
import cdm.product.asset.metafields.FieldWithMetaIndexAnnexSourceEnum;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class CreditIndexReferenceInformationOnlyExistsValidator implements ValidatorWithArg<CreditIndexReferenceInformation, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CreditIndexReferenceInformation> ValidationResult<CreditIndexReferenceInformation> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("indexName", ExistenceChecker.isSet((FieldWithMetaString) o.getIndexName()))
				.put("indexId", ExistenceChecker.isSet((List<? extends FieldWithMetaString>) o.getIndexId()))
				.put("indexSeries", ExistenceChecker.isSet((Integer) o.getIndexSeries()))
				.put("indexAnnexVersion", ExistenceChecker.isSet((Integer) o.getIndexAnnexVersion()))
				.put("indexAnnexDate", ExistenceChecker.isSet((Date) o.getIndexAnnexDate()))
				.put("indexAnnexSource", ExistenceChecker.isSet((FieldWithMetaIndexAnnexSourceEnum) o.getIndexAnnexSource()))
				.put("excludedReferenceEntity", ExistenceChecker.isSet((List<? extends ReferenceInformation>) o.getExcludedReferenceEntity()))
				.put("tranche", ExistenceChecker.isSet((Tranche) o.getTranche()))
				.put("settledEntityMatrix", ExistenceChecker.isSet((SettledEntityMatrix) o.getSettledEntityMatrix()))
				.put("indexFactor", ExistenceChecker.isSet((BigDecimal) o.getIndexFactor()))
				.put("seniority", ExistenceChecker.isSet((CreditSeniorityEnum) o.getSeniority()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CreditIndexReferenceInformation", ValidationType.ONLY_EXISTS, "CreditIndexReferenceInformation", path, "");
		}
		return failure("CreditIndexReferenceInformation", ValidationType.ONLY_EXISTS, "CreditIndexReferenceInformation", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
