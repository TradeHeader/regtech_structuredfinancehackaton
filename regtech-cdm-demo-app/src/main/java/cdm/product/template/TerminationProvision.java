package cdm.product.template;

import cdm.product.template.CancelableProvision;
import cdm.product.template.EarlyTerminationProvision;
import cdm.product.template.EvergreenProvision;
import cdm.product.template.ExtendibleProvision;
import cdm.product.template.TerminationProvision;
import cdm.product.template.TerminationProvision.TerminationProvisionBuilder;
import cdm.product.template.TerminationProvision.TerminationProvisionBuilderImpl;
import cdm.product.template.TerminationProvision.TerminationProvisionImpl;
import cdm.product.template.meta.TerminationProvisionMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * A class for defining option provisions.
 * @version ${project.version}
 */
@RosettaDataType(value="TerminationProvision", builder=TerminationProvision.TerminationProvisionBuilderImpl.class, version="${project.version}")
public interface TerminationProvision extends RosettaModelObject {

	TerminationProvisionMeta metaData = new TerminationProvisionMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * A provision that allows the specification of an embedded option within a swap giving the buyer of the option the right to terminate the swap, in whole or in part, on the early termination date.
	 */
	CancelableProvision getCancelableProvision();
	/**
	 * Parameters specifying provisions relating to the optional and mandatory early termination of a swap transaction.
	 */
	EarlyTerminationProvision getEarlyTerminationProvision();
	/**
	 * A data defining: the right of a party to exercise an Evergreen option
	 */
	EvergreenProvision getEvergreenProvision();
	/**
	 * A provision that allows the specification of an embedded option with a swap giving the buyer of the option the right to extend the swap, in whole or in part, to the extended termination date.
	 */
	ExtendibleProvision getExtendibleProvision();

	/*********************** Build Methods  ***********************/
	TerminationProvision build();
	
	TerminationProvision.TerminationProvisionBuilder toBuilder();
	
	static TerminationProvision.TerminationProvisionBuilder builder() {
		return new TerminationProvision.TerminationProvisionBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends TerminationProvision> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends TerminationProvision> getType() {
		return TerminationProvision.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("cancelableProvision"), processor, CancelableProvision.class, getCancelableProvision());
		processRosetta(path.newSubPath("earlyTerminationProvision"), processor, EarlyTerminationProvision.class, getEarlyTerminationProvision());
		processRosetta(path.newSubPath("evergreenProvision"), processor, EvergreenProvision.class, getEvergreenProvision());
		processRosetta(path.newSubPath("extendibleProvision"), processor, ExtendibleProvision.class, getExtendibleProvision());
	}
	

	/*********************** Builder Interface  ***********************/
	interface TerminationProvisionBuilder extends TerminationProvision, RosettaModelObjectBuilder {
		CancelableProvision.CancelableProvisionBuilder getOrCreateCancelableProvision();
		CancelableProvision.CancelableProvisionBuilder getCancelableProvision();
		EarlyTerminationProvision.EarlyTerminationProvisionBuilder getOrCreateEarlyTerminationProvision();
		EarlyTerminationProvision.EarlyTerminationProvisionBuilder getEarlyTerminationProvision();
		EvergreenProvision.EvergreenProvisionBuilder getOrCreateEvergreenProvision();
		EvergreenProvision.EvergreenProvisionBuilder getEvergreenProvision();
		ExtendibleProvision.ExtendibleProvisionBuilder getOrCreateExtendibleProvision();
		ExtendibleProvision.ExtendibleProvisionBuilder getExtendibleProvision();
		TerminationProvision.TerminationProvisionBuilder setCancelableProvision(CancelableProvision cancelableProvision);
		TerminationProvision.TerminationProvisionBuilder setEarlyTerminationProvision(EarlyTerminationProvision earlyTerminationProvision);
		TerminationProvision.TerminationProvisionBuilder setEvergreenProvision(EvergreenProvision evergreenProvision);
		TerminationProvision.TerminationProvisionBuilder setExtendibleProvision(ExtendibleProvision extendibleProvision);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("cancelableProvision"), processor, CancelableProvision.CancelableProvisionBuilder.class, getCancelableProvision());
			processRosetta(path.newSubPath("earlyTerminationProvision"), processor, EarlyTerminationProvision.EarlyTerminationProvisionBuilder.class, getEarlyTerminationProvision());
			processRosetta(path.newSubPath("evergreenProvision"), processor, EvergreenProvision.EvergreenProvisionBuilder.class, getEvergreenProvision());
			processRosetta(path.newSubPath("extendibleProvision"), processor, ExtendibleProvision.ExtendibleProvisionBuilder.class, getExtendibleProvision());
		}
		

		TerminationProvision.TerminationProvisionBuilder prune();
	}

	/*********************** Immutable Implementation of TerminationProvision  ***********************/
	class TerminationProvisionImpl implements TerminationProvision {
		private final CancelableProvision cancelableProvision;
		private final EarlyTerminationProvision earlyTerminationProvision;
		private final EvergreenProvision evergreenProvision;
		private final ExtendibleProvision extendibleProvision;
		
		protected TerminationProvisionImpl(TerminationProvision.TerminationProvisionBuilder builder) {
			this.cancelableProvision = ofNullable(builder.getCancelableProvision()).map(f->f.build()).orElse(null);
			this.earlyTerminationProvision = ofNullable(builder.getEarlyTerminationProvision()).map(f->f.build()).orElse(null);
			this.evergreenProvision = ofNullable(builder.getEvergreenProvision()).map(f->f.build()).orElse(null);
			this.extendibleProvision = ofNullable(builder.getExtendibleProvision()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("cancelableProvision")
		public CancelableProvision getCancelableProvision() {
			return cancelableProvision;
		}
		
		@Override
		@RosettaAttribute("earlyTerminationProvision")
		public EarlyTerminationProvision getEarlyTerminationProvision() {
			return earlyTerminationProvision;
		}
		
		@Override
		@RosettaAttribute("evergreenProvision")
		public EvergreenProvision getEvergreenProvision() {
			return evergreenProvision;
		}
		
		@Override
		@RosettaAttribute("extendibleProvision")
		public ExtendibleProvision getExtendibleProvision() {
			return extendibleProvision;
		}
		
		@Override
		public TerminationProvision build() {
			return this;
		}
		
		@Override
		public TerminationProvision.TerminationProvisionBuilder toBuilder() {
			TerminationProvision.TerminationProvisionBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(TerminationProvision.TerminationProvisionBuilder builder) {
			ofNullable(getCancelableProvision()).ifPresent(builder::setCancelableProvision);
			ofNullable(getEarlyTerminationProvision()).ifPresent(builder::setEarlyTerminationProvision);
			ofNullable(getEvergreenProvision()).ifPresent(builder::setEvergreenProvision);
			ofNullable(getExtendibleProvision()).ifPresent(builder::setExtendibleProvision);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			TerminationProvision _that = getType().cast(o);
		
			if (!Objects.equals(cancelableProvision, _that.getCancelableProvision())) return false;
			if (!Objects.equals(earlyTerminationProvision, _that.getEarlyTerminationProvision())) return false;
			if (!Objects.equals(evergreenProvision, _that.getEvergreenProvision())) return false;
			if (!Objects.equals(extendibleProvision, _that.getExtendibleProvision())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (cancelableProvision != null ? cancelableProvision.hashCode() : 0);
			_result = 31 * _result + (earlyTerminationProvision != null ? earlyTerminationProvision.hashCode() : 0);
			_result = 31 * _result + (evergreenProvision != null ? evergreenProvision.hashCode() : 0);
			_result = 31 * _result + (extendibleProvision != null ? extendibleProvision.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "TerminationProvision {" +
				"cancelableProvision=" + this.cancelableProvision + ", " +
				"earlyTerminationProvision=" + this.earlyTerminationProvision + ", " +
				"evergreenProvision=" + this.evergreenProvision + ", " +
				"extendibleProvision=" + this.extendibleProvision +
			'}';
		}
	}

	/*********************** Builder Implementation of TerminationProvision  ***********************/
	class TerminationProvisionBuilderImpl implements TerminationProvision.TerminationProvisionBuilder {
	
		protected CancelableProvision.CancelableProvisionBuilder cancelableProvision;
		protected EarlyTerminationProvision.EarlyTerminationProvisionBuilder earlyTerminationProvision;
		protected EvergreenProvision.EvergreenProvisionBuilder evergreenProvision;
		protected ExtendibleProvision.ExtendibleProvisionBuilder extendibleProvision;
	
		public TerminationProvisionBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("cancelableProvision")
		public CancelableProvision.CancelableProvisionBuilder getCancelableProvision() {
			return cancelableProvision;
		}
		
		@Override
		public CancelableProvision.CancelableProvisionBuilder getOrCreateCancelableProvision() {
			CancelableProvision.CancelableProvisionBuilder result;
			if (cancelableProvision!=null) {
				result = cancelableProvision;
			}
			else {
				result = cancelableProvision = CancelableProvision.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("earlyTerminationProvision")
		public EarlyTerminationProvision.EarlyTerminationProvisionBuilder getEarlyTerminationProvision() {
			return earlyTerminationProvision;
		}
		
		@Override
		public EarlyTerminationProvision.EarlyTerminationProvisionBuilder getOrCreateEarlyTerminationProvision() {
			EarlyTerminationProvision.EarlyTerminationProvisionBuilder result;
			if (earlyTerminationProvision!=null) {
				result = earlyTerminationProvision;
			}
			else {
				result = earlyTerminationProvision = EarlyTerminationProvision.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("evergreenProvision")
		public EvergreenProvision.EvergreenProvisionBuilder getEvergreenProvision() {
			return evergreenProvision;
		}
		
		@Override
		public EvergreenProvision.EvergreenProvisionBuilder getOrCreateEvergreenProvision() {
			EvergreenProvision.EvergreenProvisionBuilder result;
			if (evergreenProvision!=null) {
				result = evergreenProvision;
			}
			else {
				result = evergreenProvision = EvergreenProvision.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("extendibleProvision")
		public ExtendibleProvision.ExtendibleProvisionBuilder getExtendibleProvision() {
			return extendibleProvision;
		}
		
		@Override
		public ExtendibleProvision.ExtendibleProvisionBuilder getOrCreateExtendibleProvision() {
			ExtendibleProvision.ExtendibleProvisionBuilder result;
			if (extendibleProvision!=null) {
				result = extendibleProvision;
			}
			else {
				result = extendibleProvision = ExtendibleProvision.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("cancelableProvision")
		public TerminationProvision.TerminationProvisionBuilder setCancelableProvision(CancelableProvision cancelableProvision) {
			this.cancelableProvision = cancelableProvision==null?null:cancelableProvision.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("earlyTerminationProvision")
		public TerminationProvision.TerminationProvisionBuilder setEarlyTerminationProvision(EarlyTerminationProvision earlyTerminationProvision) {
			this.earlyTerminationProvision = earlyTerminationProvision==null?null:earlyTerminationProvision.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("evergreenProvision")
		public TerminationProvision.TerminationProvisionBuilder setEvergreenProvision(EvergreenProvision evergreenProvision) {
			this.evergreenProvision = evergreenProvision==null?null:evergreenProvision.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("extendibleProvision")
		public TerminationProvision.TerminationProvisionBuilder setExtendibleProvision(ExtendibleProvision extendibleProvision) {
			this.extendibleProvision = extendibleProvision==null?null:extendibleProvision.toBuilder();
			return this;
		}
		
		@Override
		public TerminationProvision build() {
			return new TerminationProvision.TerminationProvisionImpl(this);
		}
		
		@Override
		public TerminationProvision.TerminationProvisionBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public TerminationProvision.TerminationProvisionBuilder prune() {
			if (cancelableProvision!=null && !cancelableProvision.prune().hasData()) cancelableProvision = null;
			if (earlyTerminationProvision!=null && !earlyTerminationProvision.prune().hasData()) earlyTerminationProvision = null;
			if (evergreenProvision!=null && !evergreenProvision.prune().hasData()) evergreenProvision = null;
			if (extendibleProvision!=null && !extendibleProvision.prune().hasData()) extendibleProvision = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getCancelableProvision()!=null && getCancelableProvision().hasData()) return true;
			if (getEarlyTerminationProvision()!=null && getEarlyTerminationProvision().hasData()) return true;
			if (getEvergreenProvision()!=null && getEvergreenProvision().hasData()) return true;
			if (getExtendibleProvision()!=null && getExtendibleProvision().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public TerminationProvision.TerminationProvisionBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			TerminationProvision.TerminationProvisionBuilder o = (TerminationProvision.TerminationProvisionBuilder) other;
			
			merger.mergeRosetta(getCancelableProvision(), o.getCancelableProvision(), this::setCancelableProvision);
			merger.mergeRosetta(getEarlyTerminationProvision(), o.getEarlyTerminationProvision(), this::setEarlyTerminationProvision);
			merger.mergeRosetta(getEvergreenProvision(), o.getEvergreenProvision(), this::setEvergreenProvision);
			merger.mergeRosetta(getExtendibleProvision(), o.getExtendibleProvision(), this::setExtendibleProvision);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			TerminationProvision _that = getType().cast(o);
		
			if (!Objects.equals(cancelableProvision, _that.getCancelableProvision())) return false;
			if (!Objects.equals(earlyTerminationProvision, _that.getEarlyTerminationProvision())) return false;
			if (!Objects.equals(evergreenProvision, _that.getEvergreenProvision())) return false;
			if (!Objects.equals(extendibleProvision, _that.getExtendibleProvision())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (cancelableProvision != null ? cancelableProvision.hashCode() : 0);
			_result = 31 * _result + (earlyTerminationProvision != null ? earlyTerminationProvision.hashCode() : 0);
			_result = 31 * _result + (evergreenProvision != null ? evergreenProvision.hashCode() : 0);
			_result = 31 * _result + (extendibleProvision != null ? extendibleProvision.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "TerminationProvisionBuilder {" +
				"cancelableProvision=" + this.cancelableProvision + ", " +
				"earlyTerminationProvision=" + this.earlyTerminationProvision + ", " +
				"evergreenProvision=" + this.evergreenProvision + ", " +
				"extendibleProvision=" + this.extendibleProvision +
			'}';
		}
	}
}
