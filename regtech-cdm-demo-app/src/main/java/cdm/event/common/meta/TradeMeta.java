package cdm.event.common.meta;

import cdm.event.common.Trade;
import cdm.event.common.validation.TradeTypeFormatValidator;
import cdm.event.common.validation.TradeValidator;
import cdm.event.common.validation.exists.TradeOnlyExistsValidator;
import com.rosetta.model.lib.annotations.RosettaMeta;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.qualify.QualifyFunctionFactory;
import com.rosetta.model.lib.qualify.QualifyResult;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.lib.validation.ValidatorFactory;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;


/**
 * @version ${project.version}
 */
@RosettaMeta(model=Trade.class)
public class TradeMeta implements RosettaMetaData<Trade> {

	@Override
	public List<Validator<? super Trade>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.event.common.validation.datarule.TradeSecurityPartyRoleBuyerSeller.class),
			factory.create(cdm.event.common.validation.datarule.TradeSecurityPrice.class),
			factory.create(cdm.event.common.validation.datarule.TradeSettlementTerms.class),
			factory.create(cdm.event.common.validation.datarule.TradePackageTrade.class),
			factory.create(cdm.event.common.validation.datarule.TradeDeliverableObligationsPhysicalSettlementMatrix.class),
			factory.create(cdm.event.common.validation.datarule.TradeObligationsPhysicalSettlementMatrix.class),
			factory.create(cdm.event.common.validation.datarule.TradeCreditEventsPhysicalSettlementMatrix.class),
			factory.create(cdm.event.common.validation.datarule.TradeRestructuringPhysicalSettlementMatrix.class),
			factory.create(cdm.event.common.validation.datarule.TradeAdditionalFixedPaymentsMortgages.class),
			factory.create(cdm.event.common.validation.datarule.TradeFloatingAmountEventsMortgages.class),
			factory.create(cdm.event.common.validation.datarule.TradeCreditEventsMortgages.class),
			factory.create(cdm.event.common.validation.datarule.TradeHedgingParty.class),
			factory.create(cdm.event.common.validation.datarule.TradeDeterminingParty.class),
			factory.create(cdm.event.common.validation.datarule.TradeBarrierDerterminationAgent.class),
			factory.create(cdm.event.common.validation.datarule.TradeClearedDate.class),
			factory.create(cdm.event.common.validation.datarule.TradeFpMLCd1.class),
			factory.create(cdm.event.common.validation.datarule.TradeFpMLCd7.class),
			factory.create(cdm.event.common.validation.datarule.TradeFpMLCd8.class),
			factory.create(cdm.event.common.validation.datarule.TradeFpMLCd11.class),
			factory.create(cdm.event.common.validation.datarule.TradeFpMLCd19.class),
			factory.create(cdm.event.common.validation.datarule.TradeFpMLCd20.class),
			factory.create(cdm.event.common.validation.datarule.TradeFpMLCd23.class),
			factory.create(cdm.event.common.validation.datarule.TradeFpMLCd24.class),
			factory.create(cdm.event.common.validation.datarule.TradeFpMLCd25.class),
			factory.create(cdm.event.common.validation.datarule.TradeFpMLCd32.class),
			factory.create(cdm.event.common.validation.datarule.TradeFpMLIrd8.class),
			factory.create(cdm.event.common.validation.datarule.TradeExtraordinaryEvents.class),
			factory.create(cdm.event.common.validation.datarule.TradeDisruptionEventsDeterminingParty.class)
		);
	}
	
	@Override
	public List<Function<? super Trade, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Trade> validator() {
		return new TradeValidator();
	}

	@Override
	public Validator<? super Trade> typeFormatValidator() {
		return new TradeTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Trade, Set<String>> onlyExistsValidator() {
		return new TradeOnlyExistsValidator();
	}
}
