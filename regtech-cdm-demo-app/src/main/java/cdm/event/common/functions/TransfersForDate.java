package cdm.event.common.functions;

import cdm.base.datetime.AdjustableOrAdjustedOrRelativeDate;
import cdm.event.common.Transfer;
import cdm.event.common.Transfer.TransferBuilder;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.metafields.FieldWithMetaDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(TransfersForDate.TransfersForDateDefault.class)
public abstract class TransfersForDate implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param transfers 
	* @param date 
	* @return transfersForDate 
	*/
	public List<? extends Transfer> evaluate(List<? extends Transfer> transfers, Date date) {
		List<Transfer.TransferBuilder> transfersForDateBuilder = doEvaluate(transfers, date);
		
		final List<? extends Transfer> transfersForDate;
		if (transfersForDateBuilder == null) {
			transfersForDate = null;
		} else {
			transfersForDate = transfersForDateBuilder.stream().map(Transfer::build).collect(Collectors.toList());
			objectValidator.validate(Transfer.class, transfersForDate);
		}
		
		return transfersForDate;
	}

	protected abstract List<Transfer.TransferBuilder> doEvaluate(List<? extends Transfer> transfers, Date date);

	public static class TransfersForDateDefault extends TransfersForDate {
		@Override
		protected List<Transfer.TransferBuilder> doEvaluate(List<? extends Transfer> transfers, Date date) {
			if (transfers == null) {
				transfers = Collections.emptyList();
			}
			List<Transfer.TransferBuilder> transfersForDate = new ArrayList<>();
			return assignOutput(transfersForDate, transfers, date);
		}
		
		protected List<Transfer.TransferBuilder> assignOutput(List<Transfer.TransferBuilder> transfersForDate, List<? extends Transfer> transfers, Date date) {
			transfersForDate.addAll(toBuilder(MapperC.<Transfer>of(transfers)
				.filterItemNullSafe(item -> areEqual(item.<AdjustableOrAdjustedOrRelativeDate>map("getSettlementDate", transferBase -> transferBase.getSettlementDate()).<FieldWithMetaDate>map("getAdjustedDate", adjustableOrAdjustedOrRelativeDate -> adjustableOrAdjustedOrRelativeDate.getAdjustedDate()).<Date>map("getValue", _f->_f.getValue()), MapperS.of(date), CardinalityOperator.All).get()).getMulti()));
			
			return Optional.ofNullable(transfersForDate)
				.map(o -> o.stream().map(i -> i.prune()).collect(Collectors.toList()))
				.orElse(null);
		}
	}
}
