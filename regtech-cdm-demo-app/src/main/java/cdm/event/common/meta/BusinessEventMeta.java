package cdm.event.common.meta;

import cdm.event.common.BusinessEvent;
import cdm.event.common.functions.Qualify_Adjustment;
import cdm.event.common.functions.Qualify_Cancellation;
import cdm.event.common.functions.Qualify_OnDemandPayment;
import cdm.event.common.functions.Qualify_OnDemandRateChange;
import cdm.event.common.functions.Qualify_PairOff;
import cdm.event.common.functions.Qualify_PartialDelivery;
import cdm.event.common.functions.Qualify_Reprice;
import cdm.event.common.functions.Qualify_Repurchase;
import cdm.event.common.functions.Qualify_Roll;
import cdm.event.common.functions.Qualify_Shaping;
import cdm.event.common.functions.Qualify_Substitution;
import cdm.event.common.validation.BusinessEventTypeFormatValidator;
import cdm.event.common.validation.BusinessEventValidator;
import cdm.event.common.validation.exists.BusinessEventOnlyExistsValidator;
import cdm.event.qualification.functions.Qualify_Allocation;
import cdm.event.qualification.functions.Qualify_CashAndSecurityTransfer;
import cdm.event.qualification.functions.Qualify_CashTransfer;
import cdm.event.qualification.functions.Qualify_ClearedTrade;
import cdm.event.qualification.functions.Qualify_Compression;
import cdm.event.qualification.functions.Qualify_ContractFormation;
import cdm.event.qualification.functions.Qualify_CorporateActionDetermined;
import cdm.event.qualification.functions.Qualify_CreditEventDetermined;
import cdm.event.qualification.functions.Qualify_Execution;
import cdm.event.qualification.functions.Qualify_Exercise;
import cdm.event.qualification.functions.Qualify_FullReturn;
import cdm.event.qualification.functions.Qualify_Increase;
import cdm.event.qualification.functions.Qualify_IndexTransition;
import cdm.event.qualification.functions.Qualify_Novation;
import cdm.event.qualification.functions.Qualify_OpenOfferClearedTrade;
import cdm.event.qualification.functions.Qualify_PartialNovation;
import cdm.event.qualification.functions.Qualify_PartialTermination;
import cdm.event.qualification.functions.Qualify_PortfolioRebalancing;
import cdm.event.qualification.functions.Qualify_Reallocation;
import cdm.event.qualification.functions.Qualify_Renegotiation;
import cdm.event.qualification.functions.Qualify_Reset;
import cdm.event.qualification.functions.Qualify_SecuritySettlement;
import cdm.event.qualification.functions.Qualify_SecurityTransfer;
import cdm.event.qualification.functions.Qualify_StockSplit;
import cdm.event.qualification.functions.Qualify_Termination;
import cdm.event.qualification.functions.Qualify_ValuationUpdate;
import com.rosetta.model.lib.annotations.RosettaMeta;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.qualify.QualifyFunctionFactory;
import com.rosetta.model.lib.qualify.QualifyResult;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.lib.validation.ValidatorFactory;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Function;


/**
 * @version ${project.version}
 */
@RosettaMeta(model=BusinessEvent.class)
public class BusinessEventMeta implements RosettaMetaData<BusinessEvent> {

	@Override
	public List<Validator<? super BusinessEvent>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.event.common.validation.datarule.BusinessEventEventDate.class),
			factory.create(cdm.event.workflow.validation.datarule.EventInstructionCorporateAction.class)
		);
	}
	
	@Override
	public List<Function<? super BusinessEvent, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Arrays.asList(
			factory.create(Qualify_Allocation.class),
			factory.create(Qualify_CashTransfer.class),
			factory.create(Qualify_CashAndSecurityTransfer.class),
			factory.create(Qualify_ClearedTrade.class),
			factory.create(Qualify_OpenOfferClearedTrade.class),
			factory.create(Qualify_Compression.class),
			factory.create(Qualify_PortfolioRebalancing.class),
			factory.create(Qualify_Exercise.class),
			factory.create(Qualify_Increase.class),
			factory.create(Qualify_Novation.class),
			factory.create(Qualify_PartialNovation.class),
			factory.create(Qualify_PartialTermination.class),
			factory.create(Qualify_Renegotiation.class),
			factory.create(Qualify_Reset.class),
			factory.create(Qualify_SecurityTransfer.class),
			factory.create(Qualify_SecuritySettlement.class),
			factory.create(Qualify_Termination.class),
			factory.create(Qualify_Execution.class),
			factory.create(Qualify_ContractFormation.class),
			factory.create(Qualify_StockSplit.class),
			factory.create(Qualify_IndexTransition.class),
			factory.create(Qualify_FullReturn.class),
			factory.create(Qualify_Reallocation.class),
			factory.create(Qualify_CreditEventDetermined.class),
			factory.create(Qualify_CorporateActionDetermined.class),
			factory.create(Qualify_ValuationUpdate.class),
			factory.create(Qualify_Repurchase.class),
			factory.create(Qualify_Roll.class),
			factory.create(Qualify_OnDemandRateChange.class),
			factory.create(Qualify_Cancellation.class),
			factory.create(Qualify_PairOff.class),
			factory.create(Qualify_Shaping.class),
			factory.create(Qualify_PartialDelivery.class),
			factory.create(Qualify_Reprice.class),
			factory.create(Qualify_Adjustment.class),
			factory.create(Qualify_Substitution.class),
			factory.create(Qualify_OnDemandPayment.class)
		);
	}

	@Override
	public Validator<? super BusinessEvent> validator() {
		return new BusinessEventValidator();
	}

	@Override
	public Validator<? super BusinessEvent> typeFormatValidator() {
		return new BusinessEventTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super BusinessEvent, Set<String>> onlyExistsValidator() {
		return new BusinessEventOnlyExistsValidator();
	}
}
