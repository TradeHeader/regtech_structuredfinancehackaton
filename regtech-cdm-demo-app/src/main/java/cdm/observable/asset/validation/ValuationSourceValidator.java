package cdm.observable.asset.validation;

import cdm.base.staticdata.party.AncillaryEntity;
import cdm.base.staticdata.party.ReferenceBanks;
import cdm.observable.asset.FxSpotRateSource;
import cdm.observable.asset.SettlementRateOption;
import cdm.observable.asset.ValuationSource;
import cdm.observable.asset.metafields.ReferenceWithMetaQuotedCurrencyPair;
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

public class ValuationSourceValidator implements Validator<ValuationSource> {

	private List<ComparisonResult> getComparisonResults(ValuationSource o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("quotedCurrencyPair", (ReferenceWithMetaQuotedCurrencyPair) o.getQuotedCurrencyPair() != null ? 1 : 0, 0, 1), 
				checkCardinality("informationSource", (FxSpotRateSource) o.getInformationSource() != null ? 1 : 0, 0, 1), 
				checkCardinality("settlementRateOption", (SettlementRateOption) o.getSettlementRateOption() != null ? 1 : 0, 0, 1), 
				checkCardinality("referenceBanks", (ReferenceBanks) o.getReferenceBanks() != null ? 1 : 0, 0, 1), 
				checkCardinality("dealerOrCCP", (AncillaryEntity) o.getDealerOrCCP() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<ValuationSource> validate(RosettaPath path, ValuationSource o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ValuationSource", ValidationType.CARDINALITY, "ValuationSource", path, "", error);
		}
		return success("ValuationSource", ValidationType.CARDINALITY, "ValuationSource", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ValuationSource o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ValuationSource", ValidationType.CARDINALITY, "ValuationSource", path, "", res.getError());
				}
				return success("ValuationSource", ValidationType.CARDINALITY, "ValuationSource", path, "");
			})
			.collect(toList());
	}

}
