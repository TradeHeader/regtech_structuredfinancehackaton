package cdm.event.common.functions;

import cdm.event.common.TransferState;
import cdm.event.common.TransferState.TransferStateBuilder;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;


@ImplementedBy(EmptyTransferHistory.EmptyTransferHistoryDefault.class)
public abstract class EmptyTransferHistory implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @return emptyTransferHistory 
	*/
	public List<? extends TransferState> evaluate() {
		List<TransferState.TransferStateBuilder> emptyTransferHistoryBuilder = doEvaluate();
		
		final List<? extends TransferState> emptyTransferHistory;
		if (emptyTransferHistoryBuilder == null) {
			emptyTransferHistory = null;
		} else {
			emptyTransferHistory = emptyTransferHistoryBuilder.stream().map(TransferState::build).collect(Collectors.toList());
			objectValidator.validate(TransferState.class, emptyTransferHistory);
		}
		
		return emptyTransferHistory;
	}

	protected abstract List<TransferState.TransferStateBuilder> doEvaluate();

	public static class EmptyTransferHistoryDefault extends EmptyTransferHistory {
		@Override
		protected List<TransferState.TransferStateBuilder> doEvaluate() {
			List<TransferState.TransferStateBuilder> emptyTransferHistory = new ArrayList<>();
			return assignOutput(emptyTransferHistory);
		}
		
		protected List<TransferState.TransferStateBuilder> assignOutput(List<TransferState.TransferStateBuilder> emptyTransferHistory) {
			return Optional.ofNullable(emptyTransferHistory)
				.map(o -> o.stream().map(i -> i.prune()).collect(Collectors.toList()))
				.orElse(null);
		}
	}
}
