/*
Copyright 2024 TradeHeader SL

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package com.finhackbcn.cdm;

import cdm.base.math.QuantityChangeDirectionEnum;
import cdm.base.staticdata.identifier.AssignedIdentifier;
import cdm.base.staticdata.identifier.Identifier;
import cdm.event.common.*;
import cdm.event.workflow.EventInstruction;
import cdm.event.workflow.EventTimestamp;
import cdm.event.workflow.EventTimestampQualificationEnum;
import cdm.event.workflow.WorkflowStep;
import cdm.event.workflow.functions.Create_AcceptedWorkflowStepFromInstruction;
import cdm.legaldocumentation.common.AgreementName;
import cdm.legaldocumentation.common.LegalAgreement;
import cdm.legaldocumentation.common.LegalAgreementIdentification;
import cdm.legaldocumentation.common.LegalAgreementTypeEnum;
import cdm.legaldocumentation.master.MasterAgreementTypeEnum;
import cdm.legaldocumentation.master.metafields.FieldWithMetaMasterAgreementTypeEnum;
import cdm.product.common.settlement.PriceQuantity;
import cdm.product.template.TradeLot;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.inject.Inject;
import com.regnosys.rosetta.common.serialisation.RosettaObjectMapper;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.metafields.MetaFields;
import com.finhackbcn.cdm.utils.CDMTestUtils;
import com.finhackbcn.cdm.utils.ResourcesUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Tradeheader, SL
 *
 */
public class CDMBusinessEventsTests extends CDMTestUtils {

    private static final Date event_date = Date.parse("2023-02-28");
    private static final LocalTime event_time = LocalTime.of(9, 0);

    @Inject
    Create_AcceptedWorkflowStepFromInstruction createWorkflowStepFunc;

    @Before
    public void setUp() {
        super.setUp();
    }

    /**
     * BUSINESS EVENT tests
     */

    /**
     * The contract formation primitive function represents the transition of the trade state to a legally binding legal agreement after the trade is confirmed.
     * @throws IOException
     */
    @Test
    public void mustCreateContractFormationBusinessEventAcceptedWorkflowStep() throws IOException {

        // Trade to be included in contract formation.  Note that all references are resolved here.
        TradeState beforeTradeState = ResourcesUtils.getObjectAndResolveReferences(TradeState.class, irCapfloorTradeStatePath);
        assertNotNull("before TradeState must not be null", beforeTradeState);

        ContractFormationInstruction contractFormationInstruction = buildContractFormationPrimitiveInstruction(buildISDAMasterAgreementLegalAgreement());
        PrimitiveInstruction primitiveInstruction = PrimitiveInstruction.builder().setContractFormation(contractFormationInstruction).build();

        mustAssertAcceptedWorkflowStepAsExpected(primitiveInstruction, beforeTradeState, EventIntentEnum.CONTRACT_FORMATION);
    }

    /**
     * When a transaction is executed, a new trade is instantiated.
     * The creation of the new trade it is done by the Create_Execution primitive function (notice that no before trade is required as input).
     * @throws IOException
     */
    @Test
    public void mustCreateExecutionBusinessEventAcceptedWorkflowStep() throws IOException {
        // Trade to be included in contract formation.  Note that all references are resolved here.
        TradeState beforeTradeState = ResourcesUtils.getObjectAndResolveReferences(TradeState.class, irCapfloorTradeStatePath);
        assertNotNull("before TradeState must not be null", beforeTradeState);

        //Using parsed trade as input to generate the executed tradeState. Execution does not have a before tradeState.
        ExecutionInstruction executionInstruction = buildExecutionPrimitiveInstruction(beforeTradeState);
        PrimitiveInstruction primitiveInstruction = PrimitiveInstruction.builder().setExecution(executionInstruction).build();

        mustAssertAcceptedWorkflowStepAsExpected(primitiveInstruction, beforeTradeState, null);
    }

    /**
     * In some cases, the execution and confirmation happen in one go and a contract is instantiated immediately.
     * Such contract instantiation scenario can be represented using a compositive primitive instruction that comprises both an execution and a contract formation instruction and applies to a null trade state.
     */
    @Test
    public void mustCreateExecutionAndContractFormationBusinessEventAcceptedWorkflowStep() throws IOException {
        // Trade to be included in contract formation.  Note that all references are resolved here.
        TradeState beforeTradeState = ResourcesUtils.getObjectAndResolveReferences(TradeState.class, irCapfloorTradeStatePath);
        assertNotNull("before TradeState must not be null", beforeTradeState);

        //Using parsed trade as input to generate the executed tradeState. Execution does not have a before tradeState.
        ExecutionInstruction executionInstruction = buildExecutionPrimitiveInstruction(beforeTradeState);
        ContractFormationInstruction contractFormationInstruction = buildContractFormationPrimitiveInstruction(buildISDAMasterAgreementLegalAgreement());

        PrimitiveInstruction primitiveInstruction = PrimitiveInstruction.builder()
                .setExecution(executionInstruction)
                .setContractFormation(contractFormationInstruction)
                .build();

        mustAssertAcceptedWorkflowStepAsExpected(primitiveInstruction, beforeTradeState, null);

    }

    /**
     * The quantity or price of the trade1 can be increased, decreased or replaced by a new value.
     * The direction and volume/value is specified in the QuantityChangeInstruction.
     * The operation is executed by the Create_QuantityChange function.
     */
    @Test
    public void mustCreateTerminationBusinessEventAcceptedWorkflowStep() {

    }

    /**
     * The process by which an agent, having facilitated a single swap transaction on behalf of several clients, allocates a portion of the executed swap to the clients.
     * When a trade is allocated, it results in two or more new trades, assigned to the new corresponding counterparties, and the original trade itself terminated.
     *
     * The Create_Split function splits a trade into multiple identical trades, one per each SplitBreakdown present in the SplitInstruction, and applies the PrimitiveInstructions (defined in the SplitBreakdown).
     * Notice that different primitive instructions may be defined in every breakdown.
     *
     * The Create_PartyChange primitive function, invoked for the creation of the N new trades, defines the logic for changing one of the counterparties on a trade.
     * A new trade identifier must be specified as a change of party results in a new trade.
     * An ancillary party can also be specified.
     *
     * For the breakdowns containing the QuantityChangeInstruction, PartyChangeInstruction and ContractFormationInstruction N new trades are created.
     * For the breakdown containing only the QuantityChangeInstruction, a termination of the original trade is created.
     */
    @Test
    public void mustCreateAllocationBusinessEventAcceptedWorkflowStep() {

    }

    /**
     * A novation is an agreement made between two contracting parties to allow for the substitution of a new party for an existing one.
     * As a result, the original trade is terminated and a new one, with the appropriate party replaced, is created.
     *
     * In a novation, the Create_Split function splits the trade into two identical trades, one per each SplitBreakdown
     * present in the SplitInstruction, and then applies the corresponding PrimitiveInstructions (defined in the SplitBreakdown) to each one.
     *
     * For the breakdown containing the QuantityChangeInstruction, PartyChangeInstruction and ContractFormationInstruction a new trades are created.
     * For the breakdown containing only the QuantityChangeInstruction, a termination of the original trade is created.
     */
    @Test
    public void mustCreateNovationBusinessEventAcceptedWorkflowStep() {

    }

    /**
     * PRIMITIVE INSTRUCTION test builders
     */

    /**
     * Default ContractFormationInstruction builder function test
     * @return
     */
    public static ContractFormationInstruction buildContractFormationPrimitiveInstruction(LegalAgreement legalAgreement) {
        return ContractFormationInstruction.builder()
                .addLegalAgreement(legalAgreement)
                .build();
    }

    public static LegalAgreement buildISDAMasterAgreementLegalAgreement() {
        return LegalAgreement.builder()
                .setLegalAgreementIdentification(LegalAgreementIdentification.builder()
                        .setAgreementName(AgreementName.builder()
                                .setAgreementType(LegalAgreementTypeEnum.MASTER_AGREEMENT)
                                .setMasterAgreementType(FieldWithMetaMasterAgreementTypeEnum.builder()
                                        .setValue(MasterAgreementTypeEnum.ISDA_MASTER)
                                        .setMeta(MetaFields.builder()
                                                .setScheme("http://www.fpml.org/coding-scheme/master-agreement-type").build())
                                        .build())
                                .build())
                        .build())
                .build();
    }

    public static ExecutionInstruction buildExecutionPrimitiveInstruction(TradeState tradeState) {
        return ExecutionInstruction.builder()
                .setProduct(tradeState.getTrade().getTradableProduct().getProduct())
                .setPriceQuantity(tradeState.getTrade().getTradableProduct().getTradeLot().stream().map(TradeLot::getPriceQuantity).flatMap(List::stream).collect(Collectors.toList()))
                .setCounterparty(tradeState.getTrade().getTradableProduct().getCounterparty())
                //.addAncillaryParty(AncillaryParty.builder().build())
                .setParties(tradeState.getTrade().getParty())
                //.setPartyRoles(Arrays.asList(PartyRole.builder().build()))
                //.setExecutionDetails(ExecutionDetails.builder().build())
                .setTradeDate(tradeState.getTrade().getTradeDate())
                .setTradeIdentifier(tradeState.getTrade().getTradeIdentifier())
                .build();
    }

    public static QuantityChangeInstruction buildQuantityChangeInstruction(QuantityChangeDirectionEnum direction, PriceQuantity changeQuantity) {
        return QuantityChangeInstruction.builder()
                .setDirection(direction)
                .addChange(changeQuantity)
                .build();
    }

    /**
     * Instruction workflow step builder test
     *
     * @param before Input TradeState
     * @param primitiveInstruction Set of instructions to apply in the before TradeState
     * @param eventDate Date in which the event took place
     * @param eventIntent The intent of the event
     * @return An instruction workflow step which contains a proposedEvent and before TradeState
     */
    public WorkflowStep buildProposedWorkflowStep(TradeState before, PrimitiveInstruction primitiveInstruction, Date eventDate, LocalTime eventTime, EventIntentEnum eventIntent) {
        // Create an Instruction that contains:
        // - before TradeState
        // - PrimitiveInstruction containing a ContractFormationInstruction, SplitInstruction...
        Instruction instruction = Instruction.builder()
                .setBeforeValue(before)
                .setPrimitiveInstruction(primitiveInstruction);

        // Create a workflow step instruction containing the EventInstruction, EventTimestamp and EventIdentifiers
        return WorkflowStep.builder()
                .setProposedEvent(EventInstruction.builder()
                        .addInstruction(instruction)
                        .setIntent(eventIntent)
                        .setEventDate(eventDate))
                .addTimestamp(EventTimestamp.builder()
                        .setDateTime(ZonedDateTime.of(eventDate.toLocalDate(), eventTime, ZoneOffset.UTC.normalized())) //time and time zone are defined externally
                        .setQualification(EventTimestampQualificationEnum.EVENT_CREATION_DATE_TIME))
                .addEventIdentifier(Identifier.builder()
                        .addAssignedIdentifier(AssignedIdentifier.builder().setIdentifierValue("ExecutionExamples")))
                .build(); // ensure you call build() on the function input
    }

    /**
     * ASSERTIONS
     */

    /**
     *
     * @param primitiveInstruction
     * @param tradeState
     * @param intent
     * @return
     */
    public WorkflowStep mustAssertAcceptedWorkflowStepAsExpected(PrimitiveInstruction primitiveInstruction, TradeState tradeState, EventIntentEnum intent) {

        WorkflowStep proposedWorkflowStep = buildProposedWorkflowStep(tradeState, primitiveInstruction, event_date, event_time, intent);
        assertNotNull("Instruction WorkflowStep must not be null", proposedWorkflowStep);

        WorkflowStep acceptedWorkflowStep = createWorkflowStepFunc.evaluate(proposedWorkflowStep);
        assertNotNull("Accepted WorkflowStep must not be null", acceptedWorkflowStep);
        assertNotNull("after TradeState must not be null", acceptedWorkflowStep.getBusinessEvent().getAfter());

        try {
            System.out.println(
                    String.format("*** ACCEPTED WorkflowStep - [Action = %s] [Rejected = %s] ***\n%s",
                            acceptedWorkflowStep.getAction(),
                            acceptedWorkflowStep.getRejected(),
                            RosettaObjectMapper.getNewRosettaObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(acceptedWorkflowStep)
                    )
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return acceptedWorkflowStep;
    }


}
