package cdm.event.common.validation;

import cdm.base.staticdata.identifier.Identifier;
import cdm.base.staticdata.party.Party;
import cdm.event.common.CollateralBalance;
import cdm.event.common.MarginCallExposure;
import cdm.event.common.MarginCallInstructionType;
import cdm.event.common.MarginCallResponse;
import cdm.event.common.MarginCallResponseAction;
import cdm.event.common.MarginCallResponseTypeEnum;
import cdm.event.common.RegIMRoleEnum;
import cdm.event.common.RegMarginTypeEnum;
import cdm.event.common.metafields.ReferenceWithMetaCollateralPortfolio;
import cdm.legaldocumentation.common.AgreementName;
import cdm.observable.asset.Money;
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

public class MarginCallResponseValidator implements Validator<MarginCallResponse> {

	private List<ComparisonResult> getComparisonResults(MarginCallResponse o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("instructionType", (MarginCallInstructionType) o.getInstructionType() != null ? 1 : 0, 1, 1), 
				checkCardinality("clearingBroker", (Party) o.getClearingBroker() != null ? 1 : 0, 0, 1), 
				checkCardinality("callIdentifier", (Identifier) o.getCallIdentifier() != null ? 1 : 0, 0, 1), 
				checkCardinality("callAgreementType", (AgreementName) o.getCallAgreementType() != null ? 1 : 0, 1, 1), 
				checkCardinality("agreementMinimumTransferAmount", (Money) o.getAgreementMinimumTransferAmount() != null ? 1 : 0, 0, 1), 
				checkCardinality("agreementThreshold", (Money) o.getAgreementThreshold() != null ? 1 : 0, 0, 1), 
				checkCardinality("agreementRounding", (Money) o.getAgreementRounding() != null ? 1 : 0, 0, 1), 
				checkCardinality("regMarginType", (RegMarginTypeEnum) o.getRegMarginType() != null ? 1 : 0, 1, 1), 
				checkCardinality("regIMRole", (RegIMRoleEnum) o.getRegIMRole() != null ? 1 : 0, 0, 1), 
				checkCardinality("baseCurrencyExposure", (MarginCallExposure) o.getBaseCurrencyExposure() != null ? 1 : 0, 0, 1), 
				checkCardinality("collateralPortfolio", (ReferenceWithMetaCollateralPortfolio) o.getCollateralPortfolio() != null ? 1 : 0, 0, 1), 
				checkCardinality("independentAmountBalance", (CollateralBalance) o.getIndependentAmountBalance() != null ? 1 : 0, 0, 1), 
				checkCardinality("marginCallResponseAction", (List<? extends MarginCallResponseAction>) o.getMarginCallResponseAction() == null ? 0 : ((List<? extends MarginCallResponseAction>) o.getMarginCallResponseAction()).size(), 1, 0), 
				checkCardinality("marginResponseType", (MarginCallResponseTypeEnum) o.getMarginResponseType() != null ? 1 : 0, 1, 1), 
				checkCardinality("agreedAmountBaseCurrency", (Money) o.getAgreedAmountBaseCurrency() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<MarginCallResponse> validate(RosettaPath path, MarginCallResponse o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("MarginCallResponse", ValidationType.CARDINALITY, "MarginCallResponse", path, "", error);
		}
		return success("MarginCallResponse", ValidationType.CARDINALITY, "MarginCallResponse", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, MarginCallResponse o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("MarginCallResponse", ValidationType.CARDINALITY, "MarginCallResponse", path, "", res.getError());
				}
				return success("MarginCallResponse", ValidationType.CARDINALITY, "MarginCallResponse", path, "");
			})
			.collect(toList());
	}

}
