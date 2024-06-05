package cdm.regulation.validation;

import cdm.regulation.AddtlAttrbts;
import cdm.regulation.Buyr;
import cdm.regulation.ExctgPrsn;
import cdm.regulation.FinInstrm;
import cdm.regulation.InvstmtDcsnPrsn;
import cdm.regulation.New;
import cdm.regulation.OrdrTrnsmssn;
import cdm.regulation.Sellr;
import cdm.regulation.Tx;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class NewValidator implements Validator<New> {

	private List<ComparisonResult> getComparisonResults(New o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("txId", (String) o.getTxId() != null ? 1 : 0, 1, 1), 
				checkCardinality("exctgPty", (String) o.getExctgPty() != null ? 1 : 0, 1, 1), 
				checkCardinality("invstmtPtyInd", (String) o.getInvstmtPtyInd() != null ? 1 : 0, 1, 1), 
				checkCardinality("submitgPty", (String) o.getSubmitgPty() != null ? 1 : 0, 1, 1), 
				checkCardinality("buyr", (Buyr) o.getBuyr() != null ? 1 : 0, 1, 1), 
				checkCardinality("sellr", (Sellr) o.getSellr() != null ? 1 : 0, 1, 1), 
				checkCardinality("ordrTrnsmssn", (OrdrTrnsmssn) o.getOrdrTrnsmssn() != null ? 1 : 0, 1, 1), 
				checkCardinality("tx", (Tx) o.getTx() != null ? 1 : 0, 1, 1), 
				checkCardinality("finInstrm", (FinInstrm) o.getFinInstrm() != null ? 1 : 0, 1, 1), 
				checkCardinality("invstmtDcsnPrsn", (InvstmtDcsnPrsn) o.getInvstmtDcsnPrsn() != null ? 1 : 0, 1, 1), 
				checkCardinality("exctgPrsn", (ExctgPrsn) o.getExctgPrsn() != null ? 1 : 0, 1, 1), 
				checkCardinality("addtlAttrbts", (AddtlAttrbts) o.getAddtlAttrbts() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<New> validate(RosettaPath path, New o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("New", ValidationType.CARDINALITY, "New", path, "", error);
		}
		return success("New", ValidationType.CARDINALITY, "New", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, New o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("New", ValidationType.CARDINALITY, "New", path, "", res.getError());
				}
				return success("New", ValidationType.CARDINALITY, "New", path, "");
			})
			.collect(toList());
	}

}
