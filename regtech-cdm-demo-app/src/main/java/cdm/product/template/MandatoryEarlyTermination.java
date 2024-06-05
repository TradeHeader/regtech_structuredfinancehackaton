package cdm.product.template;

import cdm.base.datetime.AdjustableDate;
import cdm.observable.asset.CalculationAgent;
import cdm.product.common.settlement.SettlementTerms;
import cdm.product.template.MandatoryEarlyTermination;
import cdm.product.template.MandatoryEarlyTermination.MandatoryEarlyTerminationBuilder;
import cdm.product.template.MandatoryEarlyTermination.MandatoryEarlyTerminationBuilderImpl;
import cdm.product.template.MandatoryEarlyTermination.MandatoryEarlyTerminationImpl;
import cdm.product.template.MandatoryEarlyTerminationAdjustedDates;
import cdm.product.template.meta.MandatoryEarlyTerminationMeta;
import com.rosetta.model.lib.GlobalKey;
import com.rosetta.model.lib.GlobalKey.GlobalKeyBuilder;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.metafields.MetaFields;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * A data to:  define an early termination provision for which exercise is mandatory.
 * @version ${project.version}
 */
@RosettaDataType(value="MandatoryEarlyTermination", builder=MandatoryEarlyTermination.MandatoryEarlyTerminationBuilderImpl.class, version="${project.version}")
public interface MandatoryEarlyTermination extends RosettaModelObject, GlobalKey {

	MandatoryEarlyTerminationMeta metaData = new MandatoryEarlyTerminationMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The early termination date associated with a mandatory early termination of a swap.
	 */
	AdjustableDate getMandatoryEarlyTerminationDate();
	/**
	 * The ISDA Calculation Agent responsible for performing duties associated with an optional early termination.
	 */
	CalculationAgent getCalculationAgent();
	/**
	 * If specified, this means that cash settlement is applicable to the transaction and defines the parameters associated with the cash settlement procedure. If not specified, then physical settlement is applicable.
	 */
	SettlementTerms getCashSettlement();
	/**
	 * The adjusted dates associated with a mandatory early termination provision. These dates have been adjusted for any applicable business day convention.
	 */
	MandatoryEarlyTerminationAdjustedDates getMandatoryEarlyTerminationAdjustedDates();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	MandatoryEarlyTermination build();
	
	MandatoryEarlyTermination.MandatoryEarlyTerminationBuilder toBuilder();
	
	static MandatoryEarlyTermination.MandatoryEarlyTerminationBuilder builder() {
		return new MandatoryEarlyTermination.MandatoryEarlyTerminationBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends MandatoryEarlyTermination> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends MandatoryEarlyTermination> getType() {
		return MandatoryEarlyTermination.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("mandatoryEarlyTerminationDate"), processor, AdjustableDate.class, getMandatoryEarlyTerminationDate());
		processRosetta(path.newSubPath("calculationAgent"), processor, CalculationAgent.class, getCalculationAgent());
		processRosetta(path.newSubPath("cashSettlement"), processor, SettlementTerms.class, getCashSettlement());
		processRosetta(path.newSubPath("mandatoryEarlyTerminationAdjustedDates"), processor, MandatoryEarlyTerminationAdjustedDates.class, getMandatoryEarlyTerminationAdjustedDates());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface MandatoryEarlyTerminationBuilder extends MandatoryEarlyTermination, RosettaModelObjectBuilder {
		AdjustableDate.AdjustableDateBuilder getOrCreateMandatoryEarlyTerminationDate();
		AdjustableDate.AdjustableDateBuilder getMandatoryEarlyTerminationDate();
		CalculationAgent.CalculationAgentBuilder getOrCreateCalculationAgent();
		CalculationAgent.CalculationAgentBuilder getCalculationAgent();
		SettlementTerms.SettlementTermsBuilder getOrCreateCashSettlement();
		SettlementTerms.SettlementTermsBuilder getCashSettlement();
		MandatoryEarlyTerminationAdjustedDates.MandatoryEarlyTerminationAdjustedDatesBuilder getOrCreateMandatoryEarlyTerminationAdjustedDates();
		MandatoryEarlyTerminationAdjustedDates.MandatoryEarlyTerminationAdjustedDatesBuilder getMandatoryEarlyTerminationAdjustedDates();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		MandatoryEarlyTermination.MandatoryEarlyTerminationBuilder setMandatoryEarlyTerminationDate(AdjustableDate mandatoryEarlyTerminationDate);
		MandatoryEarlyTermination.MandatoryEarlyTerminationBuilder setCalculationAgent(CalculationAgent calculationAgent);
		MandatoryEarlyTermination.MandatoryEarlyTerminationBuilder setCashSettlement(SettlementTerms cashSettlement);
		MandatoryEarlyTermination.MandatoryEarlyTerminationBuilder setMandatoryEarlyTerminationAdjustedDates(MandatoryEarlyTerminationAdjustedDates mandatoryEarlyTerminationAdjustedDates);
		MandatoryEarlyTermination.MandatoryEarlyTerminationBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("mandatoryEarlyTerminationDate"), processor, AdjustableDate.AdjustableDateBuilder.class, getMandatoryEarlyTerminationDate());
			processRosetta(path.newSubPath("calculationAgent"), processor, CalculationAgent.CalculationAgentBuilder.class, getCalculationAgent());
			processRosetta(path.newSubPath("cashSettlement"), processor, SettlementTerms.SettlementTermsBuilder.class, getCashSettlement());
			processRosetta(path.newSubPath("mandatoryEarlyTerminationAdjustedDates"), processor, MandatoryEarlyTerminationAdjustedDates.MandatoryEarlyTerminationAdjustedDatesBuilder.class, getMandatoryEarlyTerminationAdjustedDates());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		MandatoryEarlyTermination.MandatoryEarlyTerminationBuilder prune();
	}

	/*********************** Immutable Implementation of MandatoryEarlyTermination  ***********************/
	class MandatoryEarlyTerminationImpl implements MandatoryEarlyTermination {
		private final AdjustableDate mandatoryEarlyTerminationDate;
		private final CalculationAgent calculationAgent;
		private final SettlementTerms cashSettlement;
		private final MandatoryEarlyTerminationAdjustedDates mandatoryEarlyTerminationAdjustedDates;
		private final MetaFields meta;
		
		protected MandatoryEarlyTerminationImpl(MandatoryEarlyTermination.MandatoryEarlyTerminationBuilder builder) {
			this.mandatoryEarlyTerminationDate = ofNullable(builder.getMandatoryEarlyTerminationDate()).map(f->f.build()).orElse(null);
			this.calculationAgent = ofNullable(builder.getCalculationAgent()).map(f->f.build()).orElse(null);
			this.cashSettlement = ofNullable(builder.getCashSettlement()).map(f->f.build()).orElse(null);
			this.mandatoryEarlyTerminationAdjustedDates = ofNullable(builder.getMandatoryEarlyTerminationAdjustedDates()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("mandatoryEarlyTerminationDate")
		public AdjustableDate getMandatoryEarlyTerminationDate() {
			return mandatoryEarlyTerminationDate;
		}
		
		@Override
		@RosettaAttribute("calculationAgent")
		public CalculationAgent getCalculationAgent() {
			return calculationAgent;
		}
		
		@Override
		@RosettaAttribute("cashSettlement")
		public SettlementTerms getCashSettlement() {
			return cashSettlement;
		}
		
		@Override
		@RosettaAttribute("mandatoryEarlyTerminationAdjustedDates")
		public MandatoryEarlyTerminationAdjustedDates getMandatoryEarlyTerminationAdjustedDates() {
			return mandatoryEarlyTerminationAdjustedDates;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public MandatoryEarlyTermination build() {
			return this;
		}
		
		@Override
		public MandatoryEarlyTermination.MandatoryEarlyTerminationBuilder toBuilder() {
			MandatoryEarlyTermination.MandatoryEarlyTerminationBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(MandatoryEarlyTermination.MandatoryEarlyTerminationBuilder builder) {
			ofNullable(getMandatoryEarlyTerminationDate()).ifPresent(builder::setMandatoryEarlyTerminationDate);
			ofNullable(getCalculationAgent()).ifPresent(builder::setCalculationAgent);
			ofNullable(getCashSettlement()).ifPresent(builder::setCashSettlement);
			ofNullable(getMandatoryEarlyTerminationAdjustedDates()).ifPresent(builder::setMandatoryEarlyTerminationAdjustedDates);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			MandatoryEarlyTermination _that = getType().cast(o);
		
			if (!Objects.equals(mandatoryEarlyTerminationDate, _that.getMandatoryEarlyTerminationDate())) return false;
			if (!Objects.equals(calculationAgent, _that.getCalculationAgent())) return false;
			if (!Objects.equals(cashSettlement, _that.getCashSettlement())) return false;
			if (!Objects.equals(mandatoryEarlyTerminationAdjustedDates, _that.getMandatoryEarlyTerminationAdjustedDates())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (mandatoryEarlyTerminationDate != null ? mandatoryEarlyTerminationDate.hashCode() : 0);
			_result = 31 * _result + (calculationAgent != null ? calculationAgent.hashCode() : 0);
			_result = 31 * _result + (cashSettlement != null ? cashSettlement.hashCode() : 0);
			_result = 31 * _result + (mandatoryEarlyTerminationAdjustedDates != null ? mandatoryEarlyTerminationAdjustedDates.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "MandatoryEarlyTermination {" +
				"mandatoryEarlyTerminationDate=" + this.mandatoryEarlyTerminationDate + ", " +
				"calculationAgent=" + this.calculationAgent + ", " +
				"cashSettlement=" + this.cashSettlement + ", " +
				"mandatoryEarlyTerminationAdjustedDates=" + this.mandatoryEarlyTerminationAdjustedDates + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of MandatoryEarlyTermination  ***********************/
	class MandatoryEarlyTerminationBuilderImpl implements MandatoryEarlyTermination.MandatoryEarlyTerminationBuilder, GlobalKeyBuilder {
	
		protected AdjustableDate.AdjustableDateBuilder mandatoryEarlyTerminationDate;
		protected CalculationAgent.CalculationAgentBuilder calculationAgent;
		protected SettlementTerms.SettlementTermsBuilder cashSettlement;
		protected MandatoryEarlyTerminationAdjustedDates.MandatoryEarlyTerminationAdjustedDatesBuilder mandatoryEarlyTerminationAdjustedDates;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public MandatoryEarlyTerminationBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("mandatoryEarlyTerminationDate")
		public AdjustableDate.AdjustableDateBuilder getMandatoryEarlyTerminationDate() {
			return mandatoryEarlyTerminationDate;
		}
		
		@Override
		public AdjustableDate.AdjustableDateBuilder getOrCreateMandatoryEarlyTerminationDate() {
			AdjustableDate.AdjustableDateBuilder result;
			if (mandatoryEarlyTerminationDate!=null) {
				result = mandatoryEarlyTerminationDate;
			}
			else {
				result = mandatoryEarlyTerminationDate = AdjustableDate.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("calculationAgent")
		public CalculationAgent.CalculationAgentBuilder getCalculationAgent() {
			return calculationAgent;
		}
		
		@Override
		public CalculationAgent.CalculationAgentBuilder getOrCreateCalculationAgent() {
			CalculationAgent.CalculationAgentBuilder result;
			if (calculationAgent!=null) {
				result = calculationAgent;
			}
			else {
				result = calculationAgent = CalculationAgent.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("cashSettlement")
		public SettlementTerms.SettlementTermsBuilder getCashSettlement() {
			return cashSettlement;
		}
		
		@Override
		public SettlementTerms.SettlementTermsBuilder getOrCreateCashSettlement() {
			SettlementTerms.SettlementTermsBuilder result;
			if (cashSettlement!=null) {
				result = cashSettlement;
			}
			else {
				result = cashSettlement = SettlementTerms.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("mandatoryEarlyTerminationAdjustedDates")
		public MandatoryEarlyTerminationAdjustedDates.MandatoryEarlyTerminationAdjustedDatesBuilder getMandatoryEarlyTerminationAdjustedDates() {
			return mandatoryEarlyTerminationAdjustedDates;
		}
		
		@Override
		public MandatoryEarlyTerminationAdjustedDates.MandatoryEarlyTerminationAdjustedDatesBuilder getOrCreateMandatoryEarlyTerminationAdjustedDates() {
			MandatoryEarlyTerminationAdjustedDates.MandatoryEarlyTerminationAdjustedDatesBuilder result;
			if (mandatoryEarlyTerminationAdjustedDates!=null) {
				result = mandatoryEarlyTerminationAdjustedDates;
			}
			else {
				result = mandatoryEarlyTerminationAdjustedDates = MandatoryEarlyTerminationAdjustedDates.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("meta")
		public MetaFields.MetaFieldsBuilder getMeta() {
			return meta;
		}
		
		@Override
		public MetaFields.MetaFieldsBuilder getOrCreateMeta() {
			MetaFields.MetaFieldsBuilder result;
			if (meta!=null) {
				result = meta;
			}
			else {
				result = meta = MetaFields.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("mandatoryEarlyTerminationDate")
		public MandatoryEarlyTermination.MandatoryEarlyTerminationBuilder setMandatoryEarlyTerminationDate(AdjustableDate mandatoryEarlyTerminationDate) {
			this.mandatoryEarlyTerminationDate = mandatoryEarlyTerminationDate==null?null:mandatoryEarlyTerminationDate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("calculationAgent")
		public MandatoryEarlyTermination.MandatoryEarlyTerminationBuilder setCalculationAgent(CalculationAgent calculationAgent) {
			this.calculationAgent = calculationAgent==null?null:calculationAgent.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("cashSettlement")
		public MandatoryEarlyTermination.MandatoryEarlyTerminationBuilder setCashSettlement(SettlementTerms cashSettlement) {
			this.cashSettlement = cashSettlement==null?null:cashSettlement.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("mandatoryEarlyTerminationAdjustedDates")
		public MandatoryEarlyTermination.MandatoryEarlyTerminationBuilder setMandatoryEarlyTerminationAdjustedDates(MandatoryEarlyTerminationAdjustedDates mandatoryEarlyTerminationAdjustedDates) {
			this.mandatoryEarlyTerminationAdjustedDates = mandatoryEarlyTerminationAdjustedDates==null?null:mandatoryEarlyTerminationAdjustedDates.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public MandatoryEarlyTermination.MandatoryEarlyTerminationBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public MandatoryEarlyTermination build() {
			return new MandatoryEarlyTermination.MandatoryEarlyTerminationImpl(this);
		}
		
		@Override
		public MandatoryEarlyTermination.MandatoryEarlyTerminationBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public MandatoryEarlyTermination.MandatoryEarlyTerminationBuilder prune() {
			if (mandatoryEarlyTerminationDate!=null && !mandatoryEarlyTerminationDate.prune().hasData()) mandatoryEarlyTerminationDate = null;
			if (calculationAgent!=null && !calculationAgent.prune().hasData()) calculationAgent = null;
			if (cashSettlement!=null && !cashSettlement.prune().hasData()) cashSettlement = null;
			if (mandatoryEarlyTerminationAdjustedDates!=null && !mandatoryEarlyTerminationAdjustedDates.prune().hasData()) mandatoryEarlyTerminationAdjustedDates = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getMandatoryEarlyTerminationDate()!=null && getMandatoryEarlyTerminationDate().hasData()) return true;
			if (getCalculationAgent()!=null && getCalculationAgent().hasData()) return true;
			if (getCashSettlement()!=null && getCashSettlement().hasData()) return true;
			if (getMandatoryEarlyTerminationAdjustedDates()!=null && getMandatoryEarlyTerminationAdjustedDates().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public MandatoryEarlyTermination.MandatoryEarlyTerminationBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			MandatoryEarlyTermination.MandatoryEarlyTerminationBuilder o = (MandatoryEarlyTermination.MandatoryEarlyTerminationBuilder) other;
			
			merger.mergeRosetta(getMandatoryEarlyTerminationDate(), o.getMandatoryEarlyTerminationDate(), this::setMandatoryEarlyTerminationDate);
			merger.mergeRosetta(getCalculationAgent(), o.getCalculationAgent(), this::setCalculationAgent);
			merger.mergeRosetta(getCashSettlement(), o.getCashSettlement(), this::setCashSettlement);
			merger.mergeRosetta(getMandatoryEarlyTerminationAdjustedDates(), o.getMandatoryEarlyTerminationAdjustedDates(), this::setMandatoryEarlyTerminationAdjustedDates);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			MandatoryEarlyTermination _that = getType().cast(o);
		
			if (!Objects.equals(mandatoryEarlyTerminationDate, _that.getMandatoryEarlyTerminationDate())) return false;
			if (!Objects.equals(calculationAgent, _that.getCalculationAgent())) return false;
			if (!Objects.equals(cashSettlement, _that.getCashSettlement())) return false;
			if (!Objects.equals(mandatoryEarlyTerminationAdjustedDates, _that.getMandatoryEarlyTerminationAdjustedDates())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (mandatoryEarlyTerminationDate != null ? mandatoryEarlyTerminationDate.hashCode() : 0);
			_result = 31 * _result + (calculationAgent != null ? calculationAgent.hashCode() : 0);
			_result = 31 * _result + (cashSettlement != null ? cashSettlement.hashCode() : 0);
			_result = 31 * _result + (mandatoryEarlyTerminationAdjustedDates != null ? mandatoryEarlyTerminationAdjustedDates.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "MandatoryEarlyTerminationBuilder {" +
				"mandatoryEarlyTerminationDate=" + this.mandatoryEarlyTerminationDate + ", " +
				"calculationAgent=" + this.calculationAgent + ", " +
				"cashSettlement=" + this.cashSettlement + ", " +
				"mandatoryEarlyTerminationAdjustedDates=" + this.mandatoryEarlyTerminationAdjustedDates + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
