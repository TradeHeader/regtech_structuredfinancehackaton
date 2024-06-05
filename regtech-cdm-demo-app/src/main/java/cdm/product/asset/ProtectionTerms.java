package cdm.product.asset;

import cdm.base.staticdata.asset.credit.Obligations;
import cdm.observable.event.CreditEvents;
import cdm.product.asset.FloatingAmountEvents;
import cdm.product.asset.ProtectionTerms;
import cdm.product.asset.ProtectionTerms.ProtectionTermsBuilder;
import cdm.product.asset.ProtectionTerms.ProtectionTermsBuilderImpl;
import cdm.product.asset.ProtectionTerms.ProtectionTermsImpl;
import cdm.product.asset.meta.ProtectionTermsMeta;
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
 * A class to specify the terms for calculating a payout to protect the buyer of the swap in the case of a qualified credit event. These terms include the applicable credit events, the reference obligation, and in the case of a CDS on mortgage-backed securities, the floatingAmountEvents.
 * @version ${project.version}
 */
@RosettaDataType(value="ProtectionTerms", builder=ProtectionTerms.ProtectionTermsBuilderImpl.class, version="${project.version}")
public interface ProtectionTerms extends RosettaModelObject, GlobalKey {

	ProtectionTermsMeta metaData = new ProtectionTermsMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies the applicable Credit Events that would trigger a settlement, as specified in the related Confirmation and defined in the ISDA 2014 Credit Definition article IV section 4.1.
	 */
	CreditEvents getCreditEvents();
	/**
	 * The underlying obligations of the reference entity on which you are buying or selling protection. The credit events Failure to Pay, Obligation Acceleration, Obligation Default, Restructuring, Repudiation/Moratorium are defined with respect to these obligations.
	 */
	Obligations getObligations();
	/**
	 * This element contains the ISDA terms relating to the floating rate payment events and the implied additional fixed payments, applicable to the credit derivatives transactions on mortgage-backed securities with pay-as-you-go or physical settlement.
	 */
	FloatingAmountEvents getFloatingAmountEvents();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	ProtectionTerms build();
	
	ProtectionTerms.ProtectionTermsBuilder toBuilder();
	
	static ProtectionTerms.ProtectionTermsBuilder builder() {
		return new ProtectionTerms.ProtectionTermsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ProtectionTerms> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ProtectionTerms> getType() {
		return ProtectionTerms.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("creditEvents"), processor, CreditEvents.class, getCreditEvents());
		processRosetta(path.newSubPath("obligations"), processor, Obligations.class, getObligations());
		processRosetta(path.newSubPath("floatingAmountEvents"), processor, FloatingAmountEvents.class, getFloatingAmountEvents());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ProtectionTermsBuilder extends ProtectionTerms, RosettaModelObjectBuilder {
		CreditEvents.CreditEventsBuilder getOrCreateCreditEvents();
		CreditEvents.CreditEventsBuilder getCreditEvents();
		Obligations.ObligationsBuilder getOrCreateObligations();
		Obligations.ObligationsBuilder getObligations();
		FloatingAmountEvents.FloatingAmountEventsBuilder getOrCreateFloatingAmountEvents();
		FloatingAmountEvents.FloatingAmountEventsBuilder getFloatingAmountEvents();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		ProtectionTerms.ProtectionTermsBuilder setCreditEvents(CreditEvents creditEvents);
		ProtectionTerms.ProtectionTermsBuilder setObligations(Obligations obligations);
		ProtectionTerms.ProtectionTermsBuilder setFloatingAmountEvents(FloatingAmountEvents floatingAmountEvents);
		ProtectionTerms.ProtectionTermsBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("creditEvents"), processor, CreditEvents.CreditEventsBuilder.class, getCreditEvents());
			processRosetta(path.newSubPath("obligations"), processor, Obligations.ObligationsBuilder.class, getObligations());
			processRosetta(path.newSubPath("floatingAmountEvents"), processor, FloatingAmountEvents.FloatingAmountEventsBuilder.class, getFloatingAmountEvents());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		ProtectionTerms.ProtectionTermsBuilder prune();
	}

	/*********************** Immutable Implementation of ProtectionTerms  ***********************/
	class ProtectionTermsImpl implements ProtectionTerms {
		private final CreditEvents creditEvents;
		private final Obligations obligations;
		private final FloatingAmountEvents floatingAmountEvents;
		private final MetaFields meta;
		
		protected ProtectionTermsImpl(ProtectionTerms.ProtectionTermsBuilder builder) {
			this.creditEvents = ofNullable(builder.getCreditEvents()).map(f->f.build()).orElse(null);
			this.obligations = ofNullable(builder.getObligations()).map(f->f.build()).orElse(null);
			this.floatingAmountEvents = ofNullable(builder.getFloatingAmountEvents()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("creditEvents")
		public CreditEvents getCreditEvents() {
			return creditEvents;
		}
		
		@Override
		@RosettaAttribute("obligations")
		public Obligations getObligations() {
			return obligations;
		}
		
		@Override
		@RosettaAttribute("floatingAmountEvents")
		public FloatingAmountEvents getFloatingAmountEvents() {
			return floatingAmountEvents;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public ProtectionTerms build() {
			return this;
		}
		
		@Override
		public ProtectionTerms.ProtectionTermsBuilder toBuilder() {
			ProtectionTerms.ProtectionTermsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ProtectionTerms.ProtectionTermsBuilder builder) {
			ofNullable(getCreditEvents()).ifPresent(builder::setCreditEvents);
			ofNullable(getObligations()).ifPresent(builder::setObligations);
			ofNullable(getFloatingAmountEvents()).ifPresent(builder::setFloatingAmountEvents);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ProtectionTerms _that = getType().cast(o);
		
			if (!Objects.equals(creditEvents, _that.getCreditEvents())) return false;
			if (!Objects.equals(obligations, _that.getObligations())) return false;
			if (!Objects.equals(floatingAmountEvents, _that.getFloatingAmountEvents())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (creditEvents != null ? creditEvents.hashCode() : 0);
			_result = 31 * _result + (obligations != null ? obligations.hashCode() : 0);
			_result = 31 * _result + (floatingAmountEvents != null ? floatingAmountEvents.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ProtectionTerms {" +
				"creditEvents=" + this.creditEvents + ", " +
				"obligations=" + this.obligations + ", " +
				"floatingAmountEvents=" + this.floatingAmountEvents + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of ProtectionTerms  ***********************/
	class ProtectionTermsBuilderImpl implements ProtectionTerms.ProtectionTermsBuilder, GlobalKeyBuilder {
	
		protected CreditEvents.CreditEventsBuilder creditEvents;
		protected Obligations.ObligationsBuilder obligations;
		protected FloatingAmountEvents.FloatingAmountEventsBuilder floatingAmountEvents;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public ProtectionTermsBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("creditEvents")
		public CreditEvents.CreditEventsBuilder getCreditEvents() {
			return creditEvents;
		}
		
		@Override
		public CreditEvents.CreditEventsBuilder getOrCreateCreditEvents() {
			CreditEvents.CreditEventsBuilder result;
			if (creditEvents!=null) {
				result = creditEvents;
			}
			else {
				result = creditEvents = CreditEvents.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("obligations")
		public Obligations.ObligationsBuilder getObligations() {
			return obligations;
		}
		
		@Override
		public Obligations.ObligationsBuilder getOrCreateObligations() {
			Obligations.ObligationsBuilder result;
			if (obligations!=null) {
				result = obligations;
			}
			else {
				result = obligations = Obligations.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("floatingAmountEvents")
		public FloatingAmountEvents.FloatingAmountEventsBuilder getFloatingAmountEvents() {
			return floatingAmountEvents;
		}
		
		@Override
		public FloatingAmountEvents.FloatingAmountEventsBuilder getOrCreateFloatingAmountEvents() {
			FloatingAmountEvents.FloatingAmountEventsBuilder result;
			if (floatingAmountEvents!=null) {
				result = floatingAmountEvents;
			}
			else {
				result = floatingAmountEvents = FloatingAmountEvents.builder();
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
		@RosettaAttribute("creditEvents")
		public ProtectionTerms.ProtectionTermsBuilder setCreditEvents(CreditEvents creditEvents) {
			this.creditEvents = creditEvents==null?null:creditEvents.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("obligations")
		public ProtectionTerms.ProtectionTermsBuilder setObligations(Obligations obligations) {
			this.obligations = obligations==null?null:obligations.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("floatingAmountEvents")
		public ProtectionTerms.ProtectionTermsBuilder setFloatingAmountEvents(FloatingAmountEvents floatingAmountEvents) {
			this.floatingAmountEvents = floatingAmountEvents==null?null:floatingAmountEvents.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public ProtectionTerms.ProtectionTermsBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public ProtectionTerms build() {
			return new ProtectionTerms.ProtectionTermsImpl(this);
		}
		
		@Override
		public ProtectionTerms.ProtectionTermsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ProtectionTerms.ProtectionTermsBuilder prune() {
			if (creditEvents!=null && !creditEvents.prune().hasData()) creditEvents = null;
			if (obligations!=null && !obligations.prune().hasData()) obligations = null;
			if (floatingAmountEvents!=null && !floatingAmountEvents.prune().hasData()) floatingAmountEvents = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getCreditEvents()!=null && getCreditEvents().hasData()) return true;
			if (getObligations()!=null && getObligations().hasData()) return true;
			if (getFloatingAmountEvents()!=null && getFloatingAmountEvents().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ProtectionTerms.ProtectionTermsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ProtectionTerms.ProtectionTermsBuilder o = (ProtectionTerms.ProtectionTermsBuilder) other;
			
			merger.mergeRosetta(getCreditEvents(), o.getCreditEvents(), this::setCreditEvents);
			merger.mergeRosetta(getObligations(), o.getObligations(), this::setObligations);
			merger.mergeRosetta(getFloatingAmountEvents(), o.getFloatingAmountEvents(), this::setFloatingAmountEvents);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ProtectionTerms _that = getType().cast(o);
		
			if (!Objects.equals(creditEvents, _that.getCreditEvents())) return false;
			if (!Objects.equals(obligations, _that.getObligations())) return false;
			if (!Objects.equals(floatingAmountEvents, _that.getFloatingAmountEvents())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (creditEvents != null ? creditEvents.hashCode() : 0);
			_result = 31 * _result + (obligations != null ? obligations.hashCode() : 0);
			_result = 31 * _result + (floatingAmountEvents != null ? floatingAmountEvents.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ProtectionTermsBuilder {" +
				"creditEvents=" + this.creditEvents + ", " +
				"obligations=" + this.obligations + ", " +
				"floatingAmountEvents=" + this.floatingAmountEvents + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
