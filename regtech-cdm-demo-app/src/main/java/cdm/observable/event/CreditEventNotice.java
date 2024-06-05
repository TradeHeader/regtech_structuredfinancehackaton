package cdm.observable.event;

import cdm.base.datetime.BusinessCenterEnum;
import cdm.base.staticdata.party.CounterpartyRoleEnum;
import cdm.observable.event.CreditEventNotice;
import cdm.observable.event.CreditEventNotice.CreditEventNoticeBuilder;
import cdm.observable.event.CreditEventNotice.CreditEventNoticeBuilderImpl;
import cdm.observable.event.CreditEventNotice.CreditEventNoticeImpl;
import cdm.observable.event.PubliclyAvailableInformation;
import cdm.observable.event.meta.CreditEventNoticeMeta;
import com.google.common.collect.ImmutableList;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * @version ${project.version}
 */
@RosettaDataType(value="CreditEventNotice", builder=CreditEventNotice.CreditEventNoticeBuilderImpl.class, version="${project.version}")
public interface CreditEventNotice extends RosettaModelObject {

	CreditEventNoticeMeta metaData = new CreditEventNoticeMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The notifying party is the party that notifies the other party when a credit event has occurred by means of a credit event notice. If more than one party is referenced as being the notifying party then either party may notify the other of a credit event occurring. ISDA 2003 Term: Notifying Party.
	 */
	List<CounterpartyRoleEnum> getNotifyingParty();
	/**
	 * Inclusion of this business center element implies that Greenwich Mean Time in Section 3.3 of the 2003 ISDA Credit Derivatives Definitions is replaced by the local time of the city indicated by the businessCenter element value.
	 */
	BusinessCenterEnum getBusinessCenter();
	/**
	 * A specified condition to settlement. Publicly available information means information that reasonably confirms any of the facts relevant to determining that a credit event or potential repudiation/moratorium, as applicable, has occurred. The ISDA defined list (2003) is the market standard and is considered comprehensive, and a minimum of two differing public sources must have published the relevant information, to declare a Credit Event. ISDA 2003 Term: Notice of Publicly Available Information Applicable.
	 */
	PubliclyAvailableInformation getPubliclyAvailableInformation();

	/*********************** Build Methods  ***********************/
	CreditEventNotice build();
	
	CreditEventNotice.CreditEventNoticeBuilder toBuilder();
	
	static CreditEventNotice.CreditEventNoticeBuilder builder() {
		return new CreditEventNotice.CreditEventNoticeBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CreditEventNotice> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CreditEventNotice> getType() {
		return CreditEventNotice.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("notifyingParty"), CounterpartyRoleEnum.class, getNotifyingParty(), this);
		processor.processBasic(path.newSubPath("businessCenter"), BusinessCenterEnum.class, getBusinessCenter(), this);
		processRosetta(path.newSubPath("publiclyAvailableInformation"), processor, PubliclyAvailableInformation.class, getPubliclyAvailableInformation());
	}
	

	/*********************** Builder Interface  ***********************/
	interface CreditEventNoticeBuilder extends CreditEventNotice, RosettaModelObjectBuilder {
		PubliclyAvailableInformation.PubliclyAvailableInformationBuilder getOrCreatePubliclyAvailableInformation();
		PubliclyAvailableInformation.PubliclyAvailableInformationBuilder getPubliclyAvailableInformation();
		CreditEventNotice.CreditEventNoticeBuilder addNotifyingParty(CounterpartyRoleEnum notifyingParty0);
		CreditEventNotice.CreditEventNoticeBuilder addNotifyingParty(CounterpartyRoleEnum notifyingParty1, int _idx);
		CreditEventNotice.CreditEventNoticeBuilder addNotifyingParty(List<? extends CounterpartyRoleEnum> notifyingParty2);
		CreditEventNotice.CreditEventNoticeBuilder setNotifyingParty(List<? extends CounterpartyRoleEnum> notifyingParty3);
		CreditEventNotice.CreditEventNoticeBuilder setBusinessCenter(BusinessCenterEnum businessCenter);
		CreditEventNotice.CreditEventNoticeBuilder setPubliclyAvailableInformation(PubliclyAvailableInformation publiclyAvailableInformation);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("notifyingParty"), CounterpartyRoleEnum.class, getNotifyingParty(), this);
			processor.processBasic(path.newSubPath("businessCenter"), BusinessCenterEnum.class, getBusinessCenter(), this);
			processRosetta(path.newSubPath("publiclyAvailableInformation"), processor, PubliclyAvailableInformation.PubliclyAvailableInformationBuilder.class, getPubliclyAvailableInformation());
		}
		

		CreditEventNotice.CreditEventNoticeBuilder prune();
	}

	/*********************** Immutable Implementation of CreditEventNotice  ***********************/
	class CreditEventNoticeImpl implements CreditEventNotice {
		private final List<CounterpartyRoleEnum> notifyingParty;
		private final BusinessCenterEnum businessCenter;
		private final PubliclyAvailableInformation publiclyAvailableInformation;
		
		protected CreditEventNoticeImpl(CreditEventNotice.CreditEventNoticeBuilder builder) {
			this.notifyingParty = ofNullable(builder.getNotifyingParty()).filter(_l->!_l.isEmpty()).map(ImmutableList::copyOf).orElse(null);
			this.businessCenter = builder.getBusinessCenter();
			this.publiclyAvailableInformation = ofNullable(builder.getPubliclyAvailableInformation()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("notifyingParty")
		public List<CounterpartyRoleEnum> getNotifyingParty() {
			return notifyingParty;
		}
		
		@Override
		@RosettaAttribute("businessCenter")
		public BusinessCenterEnum getBusinessCenter() {
			return businessCenter;
		}
		
		@Override
		@RosettaAttribute("publiclyAvailableInformation")
		public PubliclyAvailableInformation getPubliclyAvailableInformation() {
			return publiclyAvailableInformation;
		}
		
		@Override
		public CreditEventNotice build() {
			return this;
		}
		
		@Override
		public CreditEventNotice.CreditEventNoticeBuilder toBuilder() {
			CreditEventNotice.CreditEventNoticeBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CreditEventNotice.CreditEventNoticeBuilder builder) {
			ofNullable(getNotifyingParty()).ifPresent(builder::setNotifyingParty);
			ofNullable(getBusinessCenter()).ifPresent(builder::setBusinessCenter);
			ofNullable(getPubliclyAvailableInformation()).ifPresent(builder::setPubliclyAvailableInformation);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CreditEventNotice _that = getType().cast(o);
		
			if (!ListEquals.listEquals(notifyingParty, _that.getNotifyingParty())) return false;
			if (!Objects.equals(businessCenter, _that.getBusinessCenter())) return false;
			if (!Objects.equals(publiclyAvailableInformation, _that.getPubliclyAvailableInformation())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (notifyingParty != null ? notifyingParty.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			_result = 31 * _result + (businessCenter != null ? businessCenter.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (publiclyAvailableInformation != null ? publiclyAvailableInformation.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CreditEventNotice {" +
				"notifyingParty=" + this.notifyingParty + ", " +
				"businessCenter=" + this.businessCenter + ", " +
				"publiclyAvailableInformation=" + this.publiclyAvailableInformation +
			'}';
		}
	}

	/*********************** Builder Implementation of CreditEventNotice  ***********************/
	class CreditEventNoticeBuilderImpl implements CreditEventNotice.CreditEventNoticeBuilder {
	
		protected List<CounterpartyRoleEnum> notifyingParty = new ArrayList<>();
		protected BusinessCenterEnum businessCenter;
		protected PubliclyAvailableInformation.PubliclyAvailableInformationBuilder publiclyAvailableInformation;
	
		public CreditEventNoticeBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("notifyingParty")
		public List<CounterpartyRoleEnum> getNotifyingParty() {
			return notifyingParty;
		}
		
		@Override
		@RosettaAttribute("businessCenter")
		public BusinessCenterEnum getBusinessCenter() {
			return businessCenter;
		}
		
		@Override
		@RosettaAttribute("publiclyAvailableInformation")
		public PubliclyAvailableInformation.PubliclyAvailableInformationBuilder getPubliclyAvailableInformation() {
			return publiclyAvailableInformation;
		}
		
		@Override
		public PubliclyAvailableInformation.PubliclyAvailableInformationBuilder getOrCreatePubliclyAvailableInformation() {
			PubliclyAvailableInformation.PubliclyAvailableInformationBuilder result;
			if (publiclyAvailableInformation!=null) {
				result = publiclyAvailableInformation;
			}
			else {
				result = publiclyAvailableInformation = PubliclyAvailableInformation.builder();
			}
			
			return result;
		}
	
		@Override
		public CreditEventNotice.CreditEventNoticeBuilder addNotifyingParty(CounterpartyRoleEnum notifyingParty) {
			if (notifyingParty!=null) this.notifyingParty.add(notifyingParty);
			return this;
		}
		
		@Override
		public CreditEventNotice.CreditEventNoticeBuilder addNotifyingParty(CounterpartyRoleEnum notifyingParty, int _idx) {
			getIndex(this.notifyingParty, _idx, () -> notifyingParty);
			return this;
		}
		@Override 
		public CreditEventNotice.CreditEventNoticeBuilder addNotifyingParty(List<? extends CounterpartyRoleEnum> notifyingPartys) {
			if (notifyingPartys != null) {
				for (CounterpartyRoleEnum toAdd : notifyingPartys) {
					this.notifyingParty.add(toAdd);
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("notifyingParty")
		public CreditEventNotice.CreditEventNoticeBuilder setNotifyingParty(List<? extends CounterpartyRoleEnum> notifyingPartys) {
			if (notifyingPartys == null)  {
				this.notifyingParty = new ArrayList<>();
			}
			else {
				this.notifyingParty = notifyingPartys.stream()
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("businessCenter")
		public CreditEventNotice.CreditEventNoticeBuilder setBusinessCenter(BusinessCenterEnum businessCenter) {
			this.businessCenter = businessCenter==null?null:businessCenter;
			return this;
		}
		@Override
		@RosettaAttribute("publiclyAvailableInformation")
		public CreditEventNotice.CreditEventNoticeBuilder setPubliclyAvailableInformation(PubliclyAvailableInformation publiclyAvailableInformation) {
			this.publiclyAvailableInformation = publiclyAvailableInformation==null?null:publiclyAvailableInformation.toBuilder();
			return this;
		}
		
		@Override
		public CreditEventNotice build() {
			return new CreditEventNotice.CreditEventNoticeImpl(this);
		}
		
		@Override
		public CreditEventNotice.CreditEventNoticeBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CreditEventNotice.CreditEventNoticeBuilder prune() {
			if (publiclyAvailableInformation!=null && !publiclyAvailableInformation.prune().hasData()) publiclyAvailableInformation = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getNotifyingParty()!=null && !getNotifyingParty().isEmpty()) return true;
			if (getBusinessCenter()!=null) return true;
			if (getPubliclyAvailableInformation()!=null && getPubliclyAvailableInformation().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CreditEventNotice.CreditEventNoticeBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CreditEventNotice.CreditEventNoticeBuilder o = (CreditEventNotice.CreditEventNoticeBuilder) other;
			
			merger.mergeRosetta(getPubliclyAvailableInformation(), o.getPubliclyAvailableInformation(), this::setPubliclyAvailableInformation);
			
			merger.mergeBasic(getNotifyingParty(), o.getNotifyingParty(), (Consumer<CounterpartyRoleEnum>) this::addNotifyingParty);
			merger.mergeBasic(getBusinessCenter(), o.getBusinessCenter(), this::setBusinessCenter);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CreditEventNotice _that = getType().cast(o);
		
			if (!ListEquals.listEquals(notifyingParty, _that.getNotifyingParty())) return false;
			if (!Objects.equals(businessCenter, _that.getBusinessCenter())) return false;
			if (!Objects.equals(publiclyAvailableInformation, _that.getPubliclyAvailableInformation())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (notifyingParty != null ? notifyingParty.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			_result = 31 * _result + (businessCenter != null ? businessCenter.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (publiclyAvailableInformation != null ? publiclyAvailableInformation.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CreditEventNoticeBuilder {" +
				"notifyingParty=" + this.notifyingParty + ", " +
				"businessCenter=" + this.businessCenter + ", " +
				"publiclyAvailableInformation=" + this.publiclyAvailableInformation +
			'}';
		}
	}
}
