package cdm.event.common.validation;

import cdm.base.datetime.metafields.FieldWithMetaTimeZone;
import cdm.base.staticdata.identifier.Identifier;
import cdm.base.staticdata.party.Counterparty;
import cdm.base.staticdata.party.Party;
import cdm.event.common.ExecutionDetails;
import cdm.event.common.ExecutionInstruction;
import cdm.event.common.TradeIdentifier;
import cdm.product.collateral.Collateral;
import cdm.product.common.settlement.PriceQuantity;
import cdm.product.template.Product;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.metafields.FieldWithMetaDate;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class ExecutionInstructionValidator implements Validator<ExecutionInstruction> {

	private List<ComparisonResult> getComparisonResults(ExecutionInstruction o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("product", (Product) o.getProduct() != null ? 1 : 0, 1, 1), 
				checkCardinality("priceQuantity", (List<? extends PriceQuantity>) o.getPriceQuantity() == null ? 0 : ((List<? extends PriceQuantity>) o.getPriceQuantity()).size(), 1, 0), 
				checkCardinality("counterparty", (List<? extends Counterparty>) o.getCounterparty() == null ? 0 : ((List<? extends Counterparty>) o.getCounterparty()).size(), 2, 2), 
				checkCardinality("parties", (List<? extends Party>) o.getParties() == null ? 0 : ((List<? extends Party>) o.getParties()).size(), 2, 0), 
				checkCardinality("executionDetails", (ExecutionDetails) o.getExecutionDetails() != null ? 1 : 0, 1, 1), 
				checkCardinality("tradeDate", (FieldWithMetaDate) o.getTradeDate() != null ? 1 : 0, 1, 1), 
				checkCardinality("tradeTime", (FieldWithMetaTimeZone) o.getTradeTime() != null ? 1 : 0, 0, 1), 
				checkCardinality("tradeIdentifier", (List<? extends TradeIdentifier>) o.getTradeIdentifier() == null ? 0 : ((List<? extends TradeIdentifier>) o.getTradeIdentifier()).size(), 1, 0), 
				checkCardinality("collateral", (Collateral) o.getCollateral() != null ? 1 : 0, 0, 1), 
				checkCardinality("lotIdentifier", (Identifier) o.getLotIdentifier() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<ExecutionInstruction> validate(RosettaPath path, ExecutionInstruction o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ExecutionInstruction", ValidationType.CARDINALITY, "ExecutionInstruction", path, "", error);
		}
		return success("ExecutionInstruction", ValidationType.CARDINALITY, "ExecutionInstruction", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ExecutionInstruction o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ExecutionInstruction", ValidationType.CARDINALITY, "ExecutionInstruction", path, "", res.getError());
				}
				return success("ExecutionInstruction", ValidationType.CARDINALITY, "ExecutionInstruction", path, "");
			})
			.collect(toList());
	}

}
