package cdm.regulation.validation.exists;

import cdm.regulation.AddtlAttrbts;
import cdm.regulation.Buyr;
import cdm.regulation.ExctgPrsn;
import cdm.regulation.FinInstrm;
import cdm.regulation.InvstmtDcsnPrsn;
import cdm.regulation.New;
import cdm.regulation.OrdrTrnsmssn;
import cdm.regulation.Sellr;
import cdm.regulation.Tx;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class NewOnlyExistsValidator implements ValidatorWithArg<New, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends New> ValidationResult<New> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("txId", ExistenceChecker.isSet((String) o.getTxId()))
				.put("exctgPty", ExistenceChecker.isSet((String) o.getExctgPty()))
				.put("invstmtPtyInd", ExistenceChecker.isSet((String) o.getInvstmtPtyInd()))
				.put("submitgPty", ExistenceChecker.isSet((String) o.getSubmitgPty()))
				.put("buyr", ExistenceChecker.isSet((Buyr) o.getBuyr()))
				.put("sellr", ExistenceChecker.isSet((Sellr) o.getSellr()))
				.put("ordrTrnsmssn", ExistenceChecker.isSet((OrdrTrnsmssn) o.getOrdrTrnsmssn()))
				.put("tx", ExistenceChecker.isSet((Tx) o.getTx()))
				.put("finInstrm", ExistenceChecker.isSet((FinInstrm) o.getFinInstrm()))
				.put("invstmtDcsnPrsn", ExistenceChecker.isSet((InvstmtDcsnPrsn) o.getInvstmtDcsnPrsn()))
				.put("exctgPrsn", ExistenceChecker.isSet((ExctgPrsn) o.getExctgPrsn()))
				.put("addtlAttrbts", ExistenceChecker.isSet((AddtlAttrbts) o.getAddtlAttrbts()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("New", ValidationType.ONLY_EXISTS, "New", path, "");
		}
		return failure("New", ValidationType.ONLY_EXISTS, "New", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
