package cdm.product.common.settlement;

import cdm.product.common.settlement.PrincipalPaymentSchedule;
import cdm.product.common.settlement.PrincipalPayments;
import cdm.product.common.settlement.PrincipalPayments.PrincipalPaymentsBuilder;
import cdm.product.common.settlement.PrincipalPayments.PrincipalPaymentsBuilderImpl;
import cdm.product.common.settlement.PrincipalPayments.PrincipalPaymentsImpl;
import cdm.product.common.settlement.meta.PrincipalPaymentsMeta;
import com.google.common.collect.ImmutableList;
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
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * A class defining which principal exchanges occur for the stream.
 * @version ${project.version}
 */
@RosettaDataType(value="PrincipalPayments", builder=PrincipalPayments.PrincipalPaymentsBuilderImpl.class, version="${project.version}")
public interface PrincipalPayments extends RosettaModelObject, GlobalKey {

	PrincipalPaymentsMeta metaData = new PrincipalPaymentsMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * A true/false flag to indicate whether there is an initial exchange of principal on the effective date.
	 */
	Boolean getInitialPayment();
	/**
	 * A true/false flag to indicate whether there is a final exchange of principal on the termination date.
	 */
	Boolean getFinalPayment();
	/**
	 * A true/false flag to indicate whether there are intermediate or interim exchanges of principal during the term of the swap.
	 */
	Boolean getIntermediatePayment();
	/**
	 * Indicate the Payout legs which nominal amount may vary in regards of FX Fixing dates as determined in the product terms.
	 */
	List<String> getVaryingLegNotionalCurrency();
	/**
	 * Describe dates schedules for Principal Exchanges and related role of the parties when known.
	 */
	PrincipalPaymentSchedule getPrincipalPaymentSchedule();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	PrincipalPayments build();
	
	PrincipalPayments.PrincipalPaymentsBuilder toBuilder();
	
	static PrincipalPayments.PrincipalPaymentsBuilder builder() {
		return new PrincipalPayments.PrincipalPaymentsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends PrincipalPayments> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends PrincipalPayments> getType() {
		return PrincipalPayments.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("initialPayment"), Boolean.class, getInitialPayment(), this);
		processor.processBasic(path.newSubPath("finalPayment"), Boolean.class, getFinalPayment(), this);
		processor.processBasic(path.newSubPath("intermediatePayment"), Boolean.class, getIntermediatePayment(), this);
		processor.processBasic(path.newSubPath("varyingLegNotionalCurrency"), String.class, getVaryingLegNotionalCurrency(), this);
		processRosetta(path.newSubPath("principalPaymentSchedule"), processor, PrincipalPaymentSchedule.class, getPrincipalPaymentSchedule());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface PrincipalPaymentsBuilder extends PrincipalPayments, RosettaModelObjectBuilder {
		PrincipalPaymentSchedule.PrincipalPaymentScheduleBuilder getOrCreatePrincipalPaymentSchedule();
		PrincipalPaymentSchedule.PrincipalPaymentScheduleBuilder getPrincipalPaymentSchedule();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		PrincipalPayments.PrincipalPaymentsBuilder setInitialPayment(Boolean initialPayment);
		PrincipalPayments.PrincipalPaymentsBuilder setFinalPayment(Boolean finalPayment);
		PrincipalPayments.PrincipalPaymentsBuilder setIntermediatePayment(Boolean intermediatePayment);
		PrincipalPayments.PrincipalPaymentsBuilder addVaryingLegNotionalCurrency(String varyingLegNotionalCurrency0);
		PrincipalPayments.PrincipalPaymentsBuilder addVaryingLegNotionalCurrency(String varyingLegNotionalCurrency1, int _idx);
		PrincipalPayments.PrincipalPaymentsBuilder addVaryingLegNotionalCurrency(List<? extends String> varyingLegNotionalCurrency2);
		PrincipalPayments.PrincipalPaymentsBuilder setVaryingLegNotionalCurrency(List<? extends String> varyingLegNotionalCurrency3);
		PrincipalPayments.PrincipalPaymentsBuilder setPrincipalPaymentSchedule(PrincipalPaymentSchedule principalPaymentSchedule);
		PrincipalPayments.PrincipalPaymentsBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("initialPayment"), Boolean.class, getInitialPayment(), this);
			processor.processBasic(path.newSubPath("finalPayment"), Boolean.class, getFinalPayment(), this);
			processor.processBasic(path.newSubPath("intermediatePayment"), Boolean.class, getIntermediatePayment(), this);
			processor.processBasic(path.newSubPath("varyingLegNotionalCurrency"), String.class, getVaryingLegNotionalCurrency(), this);
			processRosetta(path.newSubPath("principalPaymentSchedule"), processor, PrincipalPaymentSchedule.PrincipalPaymentScheduleBuilder.class, getPrincipalPaymentSchedule());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		PrincipalPayments.PrincipalPaymentsBuilder prune();
	}

	/*********************** Immutable Implementation of PrincipalPayments  ***********************/
	class PrincipalPaymentsImpl implements PrincipalPayments {
		private final Boolean initialPayment;
		private final Boolean finalPayment;
		private final Boolean intermediatePayment;
		private final List<String> varyingLegNotionalCurrency;
		private final PrincipalPaymentSchedule principalPaymentSchedule;
		private final MetaFields meta;
		
		protected PrincipalPaymentsImpl(PrincipalPayments.PrincipalPaymentsBuilder builder) {
			this.initialPayment = builder.getInitialPayment();
			this.finalPayment = builder.getFinalPayment();
			this.intermediatePayment = builder.getIntermediatePayment();
			this.varyingLegNotionalCurrency = ofNullable(builder.getVaryingLegNotionalCurrency()).filter(_l->!_l.isEmpty()).map(ImmutableList::copyOf).orElse(null);
			this.principalPaymentSchedule = ofNullable(builder.getPrincipalPaymentSchedule()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("initialPayment")
		public Boolean getInitialPayment() {
			return initialPayment;
		}
		
		@Override
		@RosettaAttribute("finalPayment")
		public Boolean getFinalPayment() {
			return finalPayment;
		}
		
		@Override
		@RosettaAttribute("intermediatePayment")
		public Boolean getIntermediatePayment() {
			return intermediatePayment;
		}
		
		@Override
		@RosettaAttribute("varyingLegNotionalCurrency")
		public List<String> getVaryingLegNotionalCurrency() {
			return varyingLegNotionalCurrency;
		}
		
		@Override
		@RosettaAttribute("principalPaymentSchedule")
		public PrincipalPaymentSchedule getPrincipalPaymentSchedule() {
			return principalPaymentSchedule;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public PrincipalPayments build() {
			return this;
		}
		
		@Override
		public PrincipalPayments.PrincipalPaymentsBuilder toBuilder() {
			PrincipalPayments.PrincipalPaymentsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(PrincipalPayments.PrincipalPaymentsBuilder builder) {
			ofNullable(getInitialPayment()).ifPresent(builder::setInitialPayment);
			ofNullable(getFinalPayment()).ifPresent(builder::setFinalPayment);
			ofNullable(getIntermediatePayment()).ifPresent(builder::setIntermediatePayment);
			ofNullable(getVaryingLegNotionalCurrency()).ifPresent(builder::setVaryingLegNotionalCurrency);
			ofNullable(getPrincipalPaymentSchedule()).ifPresent(builder::setPrincipalPaymentSchedule);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PrincipalPayments _that = getType().cast(o);
		
			if (!Objects.equals(initialPayment, _that.getInitialPayment())) return false;
			if (!Objects.equals(finalPayment, _that.getFinalPayment())) return false;
			if (!Objects.equals(intermediatePayment, _that.getIntermediatePayment())) return false;
			if (!ListEquals.listEquals(varyingLegNotionalCurrency, _that.getVaryingLegNotionalCurrency())) return false;
			if (!Objects.equals(principalPaymentSchedule, _that.getPrincipalPaymentSchedule())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (initialPayment != null ? initialPayment.hashCode() : 0);
			_result = 31 * _result + (finalPayment != null ? finalPayment.hashCode() : 0);
			_result = 31 * _result + (intermediatePayment != null ? intermediatePayment.hashCode() : 0);
			_result = 31 * _result + (varyingLegNotionalCurrency != null ? varyingLegNotionalCurrency.hashCode() : 0);
			_result = 31 * _result + (principalPaymentSchedule != null ? principalPaymentSchedule.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PrincipalPayments {" +
				"initialPayment=" + this.initialPayment + ", " +
				"finalPayment=" + this.finalPayment + ", " +
				"intermediatePayment=" + this.intermediatePayment + ", " +
				"varyingLegNotionalCurrency=" + this.varyingLegNotionalCurrency + ", " +
				"principalPaymentSchedule=" + this.principalPaymentSchedule + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of PrincipalPayments  ***********************/
	class PrincipalPaymentsBuilderImpl implements PrincipalPayments.PrincipalPaymentsBuilder, GlobalKeyBuilder {
	
		protected Boolean initialPayment;
		protected Boolean finalPayment;
		protected Boolean intermediatePayment;
		protected List<String> varyingLegNotionalCurrency = new ArrayList<>();
		protected PrincipalPaymentSchedule.PrincipalPaymentScheduleBuilder principalPaymentSchedule;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public PrincipalPaymentsBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("initialPayment")
		public Boolean getInitialPayment() {
			return initialPayment;
		}
		
		@Override
		@RosettaAttribute("finalPayment")
		public Boolean getFinalPayment() {
			return finalPayment;
		}
		
		@Override
		@RosettaAttribute("intermediatePayment")
		public Boolean getIntermediatePayment() {
			return intermediatePayment;
		}
		
		@Override
		@RosettaAttribute("varyingLegNotionalCurrency")
		public List<String> getVaryingLegNotionalCurrency() {
			return varyingLegNotionalCurrency;
		}
		
		@Override
		@RosettaAttribute("principalPaymentSchedule")
		public PrincipalPaymentSchedule.PrincipalPaymentScheduleBuilder getPrincipalPaymentSchedule() {
			return principalPaymentSchedule;
		}
		
		@Override
		public PrincipalPaymentSchedule.PrincipalPaymentScheduleBuilder getOrCreatePrincipalPaymentSchedule() {
			PrincipalPaymentSchedule.PrincipalPaymentScheduleBuilder result;
			if (principalPaymentSchedule!=null) {
				result = principalPaymentSchedule;
			}
			else {
				result = principalPaymentSchedule = PrincipalPaymentSchedule.builder();
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
		@RosettaAttribute("initialPayment")
		public PrincipalPayments.PrincipalPaymentsBuilder setInitialPayment(Boolean initialPayment) {
			this.initialPayment = initialPayment==null?null:initialPayment;
			return this;
		}
		@Override
		@RosettaAttribute("finalPayment")
		public PrincipalPayments.PrincipalPaymentsBuilder setFinalPayment(Boolean finalPayment) {
			this.finalPayment = finalPayment==null?null:finalPayment;
			return this;
		}
		@Override
		@RosettaAttribute("intermediatePayment")
		public PrincipalPayments.PrincipalPaymentsBuilder setIntermediatePayment(Boolean intermediatePayment) {
			this.intermediatePayment = intermediatePayment==null?null:intermediatePayment;
			return this;
		}
		@Override
		public PrincipalPayments.PrincipalPaymentsBuilder addVaryingLegNotionalCurrency(String varyingLegNotionalCurrency) {
			if (varyingLegNotionalCurrency!=null) this.varyingLegNotionalCurrency.add(varyingLegNotionalCurrency);
			return this;
		}
		
		@Override
		public PrincipalPayments.PrincipalPaymentsBuilder addVaryingLegNotionalCurrency(String varyingLegNotionalCurrency, int _idx) {
			getIndex(this.varyingLegNotionalCurrency, _idx, () -> varyingLegNotionalCurrency);
			return this;
		}
		@Override 
		public PrincipalPayments.PrincipalPaymentsBuilder addVaryingLegNotionalCurrency(List<? extends String> varyingLegNotionalCurrencys) {
			if (varyingLegNotionalCurrencys != null) {
				for (String toAdd : varyingLegNotionalCurrencys) {
					this.varyingLegNotionalCurrency.add(toAdd);
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("varyingLegNotionalCurrency")
		public PrincipalPayments.PrincipalPaymentsBuilder setVaryingLegNotionalCurrency(List<? extends String> varyingLegNotionalCurrencys) {
			if (varyingLegNotionalCurrencys == null)  {
				this.varyingLegNotionalCurrency = new ArrayList<>();
			}
			else {
				this.varyingLegNotionalCurrency = varyingLegNotionalCurrencys.stream()
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("principalPaymentSchedule")
		public PrincipalPayments.PrincipalPaymentsBuilder setPrincipalPaymentSchedule(PrincipalPaymentSchedule principalPaymentSchedule) {
			this.principalPaymentSchedule = principalPaymentSchedule==null?null:principalPaymentSchedule.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public PrincipalPayments.PrincipalPaymentsBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public PrincipalPayments build() {
			return new PrincipalPayments.PrincipalPaymentsImpl(this);
		}
		
		@Override
		public PrincipalPayments.PrincipalPaymentsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PrincipalPayments.PrincipalPaymentsBuilder prune() {
			if (principalPaymentSchedule!=null && !principalPaymentSchedule.prune().hasData()) principalPaymentSchedule = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getInitialPayment()!=null) return true;
			if (getFinalPayment()!=null) return true;
			if (getIntermediatePayment()!=null) return true;
			if (getVaryingLegNotionalCurrency()!=null && !getVaryingLegNotionalCurrency().isEmpty()) return true;
			if (getPrincipalPaymentSchedule()!=null && getPrincipalPaymentSchedule().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PrincipalPayments.PrincipalPaymentsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			PrincipalPayments.PrincipalPaymentsBuilder o = (PrincipalPayments.PrincipalPaymentsBuilder) other;
			
			merger.mergeRosetta(getPrincipalPaymentSchedule(), o.getPrincipalPaymentSchedule(), this::setPrincipalPaymentSchedule);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getInitialPayment(), o.getInitialPayment(), this::setInitialPayment);
			merger.mergeBasic(getFinalPayment(), o.getFinalPayment(), this::setFinalPayment);
			merger.mergeBasic(getIntermediatePayment(), o.getIntermediatePayment(), this::setIntermediatePayment);
			merger.mergeBasic(getVaryingLegNotionalCurrency(), o.getVaryingLegNotionalCurrency(), (Consumer<String>) this::addVaryingLegNotionalCurrency);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PrincipalPayments _that = getType().cast(o);
		
			if (!Objects.equals(initialPayment, _that.getInitialPayment())) return false;
			if (!Objects.equals(finalPayment, _that.getFinalPayment())) return false;
			if (!Objects.equals(intermediatePayment, _that.getIntermediatePayment())) return false;
			if (!ListEquals.listEquals(varyingLegNotionalCurrency, _that.getVaryingLegNotionalCurrency())) return false;
			if (!Objects.equals(principalPaymentSchedule, _that.getPrincipalPaymentSchedule())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (initialPayment != null ? initialPayment.hashCode() : 0);
			_result = 31 * _result + (finalPayment != null ? finalPayment.hashCode() : 0);
			_result = 31 * _result + (intermediatePayment != null ? intermediatePayment.hashCode() : 0);
			_result = 31 * _result + (varyingLegNotionalCurrency != null ? varyingLegNotionalCurrency.hashCode() : 0);
			_result = 31 * _result + (principalPaymentSchedule != null ? principalPaymentSchedule.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PrincipalPaymentsBuilder {" +
				"initialPayment=" + this.initialPayment + ", " +
				"finalPayment=" + this.finalPayment + ", " +
				"intermediatePayment=" + this.intermediatePayment + ", " +
				"varyingLegNotionalCurrency=" + this.varyingLegNotionalCurrency + ", " +
				"principalPaymentSchedule=" + this.principalPaymentSchedule + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
