package cdm.event.common.functions;

import cdm.base.staticdata.asset.common.ProductIdentifier;
import cdm.base.staticdata.asset.common.metafields.FieldWithMetaProductIdentifier;
import cdm.event.common.Transfer;
import cdm.event.common.Transfer.TransferBuilder;
import cdm.observable.asset.Observable;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(FilterSecurityTransfers.FilterSecurityTransfersDefault.class)
public abstract class FilterSecurityTransfers implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param transfers 
	* @return securityTransfers 
	*/
	public List<? extends Transfer> evaluate(List<? extends Transfer> transfers) {
		List<Transfer.TransferBuilder> securityTransfersBuilder = doEvaluate(transfers);
		
		final List<? extends Transfer> securityTransfers;
		if (securityTransfersBuilder == null) {
			securityTransfers = null;
		} else {
			securityTransfers = securityTransfersBuilder.stream().map(Transfer::build).collect(Collectors.toList());
			objectValidator.validate(Transfer.class, securityTransfers);
		}
		
		return securityTransfers;
	}

	protected abstract List<Transfer.TransferBuilder> doEvaluate(List<? extends Transfer> transfers);

	public static class FilterSecurityTransfersDefault extends FilterSecurityTransfers {
		@Override
		protected List<Transfer.TransferBuilder> doEvaluate(List<? extends Transfer> transfers) {
			if (transfers == null) {
				transfers = Collections.emptyList();
			}
			List<Transfer.TransferBuilder> securityTransfers = new ArrayList<>();
			return assignOutput(securityTransfers, transfers);
		}
		
		protected List<Transfer.TransferBuilder> assignOutput(List<Transfer.TransferBuilder> securityTransfers, List<? extends Transfer> transfers) {
			securityTransfers.addAll(toBuilder(MapperC.<Transfer>of(transfers)
				.filterItemNullSafe(item -> exists(item.<Observable>map("getObservable", transferBase -> transferBase.getObservable()).<FieldWithMetaProductIdentifier>mapC("getProductIdentifier", observable -> observable.getProductIdentifier()).<ProductIdentifier>map("getValue", _f->_f.getValue())).get()).getMulti()));
			
			return Optional.ofNullable(securityTransfers)
				.map(o -> o.stream().map(i -> i.prune()).collect(Collectors.toList()))
				.orElse(null);
		}
	}
}
