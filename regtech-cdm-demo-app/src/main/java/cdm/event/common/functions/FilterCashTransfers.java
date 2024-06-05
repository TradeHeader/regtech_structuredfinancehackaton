package cdm.event.common.functions;

import cdm.base.math.NonNegativeQuantity;
import cdm.base.math.UnitType;
import cdm.event.common.Transfer;
import cdm.event.common.Transfer.TransferBuilder;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(FilterCashTransfers.FilterCashTransfersDefault.class)
public abstract class FilterCashTransfers implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param transfers 
	* @return cashTransfers 
	*/
	public List<? extends Transfer> evaluate(List<? extends Transfer> transfers) {
		List<Transfer.TransferBuilder> cashTransfersBuilder = doEvaluate(transfers);
		
		final List<? extends Transfer> cashTransfers;
		if (cashTransfersBuilder == null) {
			cashTransfers = null;
		} else {
			cashTransfers = cashTransfersBuilder.stream().map(Transfer::build).collect(Collectors.toList());
			objectValidator.validate(Transfer.class, cashTransfers);
		}
		
		return cashTransfers;
	}

	protected abstract List<Transfer.TransferBuilder> doEvaluate(List<? extends Transfer> transfers);

	public static class FilterCashTransfersDefault extends FilterCashTransfers {
		@Override
		protected List<Transfer.TransferBuilder> doEvaluate(List<? extends Transfer> transfers) {
			if (transfers == null) {
				transfers = Collections.emptyList();
			}
			List<Transfer.TransferBuilder> cashTransfers = new ArrayList<>();
			return assignOutput(cashTransfers, transfers);
		}
		
		protected List<Transfer.TransferBuilder> assignOutput(List<Transfer.TransferBuilder> cashTransfers, List<? extends Transfer> transfers) {
			cashTransfers.addAll(toBuilder(MapperC.<Transfer>of(transfers)
				.filterItemNullSafe(item -> exists(item.<NonNegativeQuantity>map("getQuantity", transferBase -> transferBase.getQuantity()).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).<FieldWithMetaString>map("getCurrency", unitType -> unitType.getCurrency()).<String>map("getValue", _f->_f.getValue())).get()).getMulti()));
			
			return Optional.ofNullable(cashTransfers)
				.map(o -> o.stream().map(i -> i.prune()).collect(Collectors.toList()))
				.orElse(null);
		}
	}
}
