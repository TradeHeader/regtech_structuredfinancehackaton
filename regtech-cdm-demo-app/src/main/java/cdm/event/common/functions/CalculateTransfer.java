package cdm.event.common.functions;

import cdm.base.math.Quantity;
import cdm.event.common.CalculateTransferInstruction;
import cdm.event.common.TradeState;
import cdm.event.common.Transfer;
import cdm.event.common.Transfer.TransferBuilder;
import cdm.product.asset.InterestRatePayout;
import cdm.product.template.AssetPayout;
import cdm.product.template.Payout;
import cdm.product.template.PerformancePayout;
import cdm.product.template.SecurityPayout;
import cdm.product.template.metafields.ReferenceWithMetaPayout;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(CalculateTransfer.CalculateTransferDefault.class)
public abstract class CalculateTransfer implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected Create_AssetTransfer create_AssetTransfer;
	@Inject protected Create_CashTransfer create_CashTransfer;
	@Inject protected Create_SecurityTransfer create_SecurityTransfer;

	/**
	* @param instruction 
	* @return transfer 
	*/
	public List<? extends Transfer> evaluate(CalculateTransferInstruction instruction) {
		List<Transfer.TransferBuilder> transferBuilder = doEvaluate(instruction);
		
		final List<? extends Transfer> transfer;
		if (transferBuilder == null) {
			transfer = null;
		} else {
			transfer = transferBuilder.stream().map(Transfer::build).collect(Collectors.toList());
			objectValidator.validate(Transfer.class, transfer);
		}
		
		return transfer;
	}

	protected abstract List<Transfer.TransferBuilder> doEvaluate(CalculateTransferInstruction instruction);

	public static class CalculateTransferDefault extends CalculateTransfer {
		@Override
		protected List<Transfer.TransferBuilder> doEvaluate(CalculateTransferInstruction instruction) {
			List<Transfer.TransferBuilder> transfer = new ArrayList<>();
			return assignOutput(transfer, instruction);
		}
		
		protected List<Transfer.TransferBuilder> assignOutput(List<Transfer.TransferBuilder> transfer0, CalculateTransferInstruction instruction) {
			if (exists(MapperS.of(instruction).<ReferenceWithMetaPayout>map("getPayout", calculateTransferInstruction -> calculateTransferInstruction.getPayout()).<Payout>map("getValue", _f->_f.getValue()).<InterestRatePayout>mapC("getInterestRatePayout", payout -> payout.getInterestRatePayout())).or(exists(MapperS.of(instruction).<ReferenceWithMetaPayout>map("getPayout", calculateTransferInstruction -> calculateTransferInstruction.getPayout()).<Payout>map("getValue", _f->_f.getValue()).<PerformancePayout>mapC("getPerformancePayout", payout -> payout.getPerformancePayout()))).or(exists(MapperS.of(instruction).<ReferenceWithMetaPayout>map("getPayout", calculateTransferInstruction -> calculateTransferInstruction.getPayout()).<Payout>map("getValue", _f->_f.getValue()).<AssetPayout>mapC("getAssetPayout", payout -> payout.getAssetPayout()))).getOrDefault(false)) {
				final Transfer transfer1 = create_CashTransfer.evaluate(instruction);
				if (transfer1 == null) {
					transfer0.addAll(toBuilder(Collections.<Transfer>emptyList()));
				} else {
					transfer0.addAll(toBuilder(Collections.singletonList(transfer1)));
				}
			} else {
				transfer0.addAll(toBuilder(Collections.<Transfer>emptyList()));
			}
			
			if (exists(MapperS.of(instruction).<ReferenceWithMetaPayout>map("getPayout", calculateTransferInstruction -> calculateTransferInstruction.getPayout()).<Payout>map("getValue", _f->_f.getValue()).<SecurityPayout>mapC("getSecurityPayout", payout -> payout.getSecurityPayout())).getOrDefault(false)) {
				final Transfer transfer2 = create_SecurityTransfer.evaluate(MapperS.of(instruction).<TradeState>map("getTradeState", calculateTransferInstruction -> calculateTransferInstruction.getTradeState()).get(), MapperS.of(instruction).<Date>map("getDate", calculateTransferInstruction -> calculateTransferInstruction.getDate()).get(), MapperS.of(instruction).<Quantity>map("getQuantity", calculateTransferInstruction -> calculateTransferInstruction.getQuantity()).get());
				if (transfer2 == null) {
					transfer0.addAll(toBuilder(Collections.<Transfer>emptyList()));
				} else {
					transfer0.addAll(toBuilder(Collections.singletonList(transfer2)));
				}
			} else {
				transfer0.addAll(toBuilder(Collections.<Transfer>emptyList()));
			}
			
			if (exists(MapperS.of(instruction).<ReferenceWithMetaPayout>map("getPayout", calculateTransferInstruction -> calculateTransferInstruction.getPayout()).<Payout>map("getValue", _f->_f.getValue()).<AssetPayout>mapC("getAssetPayout", payout -> payout.getAssetPayout())).getOrDefault(false)) {
				final Transfer transfer3 = create_AssetTransfer.evaluate(instruction);
				if (transfer3 == null) {
					transfer0.addAll(toBuilder(Collections.<Transfer>emptyList()));
				} else {
					transfer0.addAll(toBuilder(Collections.singletonList(transfer3)));
				}
			} else {
				transfer0.addAll(toBuilder(Collections.<Transfer>emptyList()));
			}
			
			return Optional.ofNullable(transfer0)
				.map(o -> o.stream().map(i -> i.prune()).collect(Collectors.toList()))
				.orElse(null);
		}
	}
}
